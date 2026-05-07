package net.mcreator.abyssoria.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.common.ForgeMod;

import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.animal.Cod;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.tags.TagKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.abyssoria.init.AbyssoriaModEntities;

import java.util.EnumSet;
import java.util.List;

public class BlackSeaDevilEntity extends WaterAnimal implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(BlackSeaDevilEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(BlackSeaDevilEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(BlackSeaDevilEntity.class, EntityDataSerializers.STRING);

	public static final TagKey<EntityType<?>> CAN_ATTRACT = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("abyssoria", "bsd_can_attract"));

	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public String animationprocedure = "empty";
	String prevAnim = "empty";
	private int attackTimer = 0;

	public BlackSeaDevilEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(AbyssoriaModEntities.BLACK_SEA_DEVIL.get(), world);
	}

	public BlackSeaDevilEntity(EntityType<BlackSeaDevilEntity> type, Level world) {
		super(type, world);
		xpReward = 5;
		setNoAi(false);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0);
		this.moveControl = new FishMoveControl(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "bsd");
	}

	public String getSyncedAnimation() { return this.entityData.get(ANIMATION); }
	public void setAnimation(String animation) { this.entityData.set(ANIMATION, animation); }
	public void setTexture(String texture) { this.entityData.set(TEXTURE, texture); }
	public String getTexture() { return this.entityData.get(TEXTURE); }

	public static void init() {
		SpawnPlacements.register(AbyssoriaModEntities.BLACK_SEA_DEVIL.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.4, true));
		this.goalSelector.addGoal(2, new AttractByLightGoal(this, 1.1D, 12.0D));
		
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, 
			(entity) -> entity.getType().is(CAN_ATTRACT)));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Cod.class, true, false));

		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 0.8, 10));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.attackTimer > 0) --this.attackTimer;
		
		if (!this.isInWater() && this.onGround() && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
			this.setOnGround(false);
			this.hasImpulse = true;
			this.playSound(SoundEvents.COD_FLOP, 1.0F, 1.0F);
		}
	}

	@Override
	public void baseTick() {
		int i = this.getAirSupply();
		super.baseTick();
		if (this.isAlive() && !this.isInWaterOrBubble()) {
			this.setAirSupply(i - 1);
			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(this.damageSources().drown(), 2.0F);
			}
		} else {
			this.setAirSupply(300);
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 12);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 6);
		builder = builder.add(Attributes.FOLLOW_RANGE, 20);
		builder = builder.add(ForgeMod.SWIM_SPEED.get(), 1.6);
		return builder;
	}

	// IA DE ATRAÇÃO COM INTERVALO DE 5 SEGUNDOS (100 Ticks)
	private static class AttractByLightGoal extends Goal {
		private final Mob attractor;
		private final double speedModifier;
		private final double range;
		private int scanTimer = 0;

		public AttractByLightGoal(Mob attractor, double speed, double range) {
			this.attractor = attractor;
			this.speedModifier = speed;
			this.range = range;
		}

		@Override
		public boolean canUse() {
			return attractor.isInWater() && attractor.getTarget() == null;
		}

		@Override
		public void tick() {
			if (--scanTimer <= 0) {
				scanTimer = 100; // 100 ticks = 5 segundos
				List<Mob> list = this.attractor.level().getEntitiesOfClass(Mob.class, this.attractor.getBoundingBox().inflate(range),
					(entity) -> entity.getType().is(BlackSeaDevilEntity.CAN_ATTRACT) && entity != attractor);
				for (Mob victim : list) {
					if (victim.isInWater() && victim.getTarget() == null) {
						victim.getNavigation().moveTo(this.attractor, this.speedModifier);
					}
				}
			}
		}
	}

	static class FishMoveControl extends MoveControl {
		private final BlackSeaDevilEntity fish;
		FishMoveControl(BlackSeaDevilEntity fish) { super(fish); this.fish = fish; }
		@Override
		public void tick() {
			if (this.fish.isInWater()) this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0, 0.005, 0));
			if (this.operation == MoveControl.Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
				double d0 = this.wantedX - this.fish.getX();
				double d1 = this.wantedY - this.fish.getY();
				double d2 = this.wantedZ - this.fish.getZ();
				double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
				d1 /= d3;
				this.fish.setYRot(this.rotlerp(this.fish.getYRot(), (float) (Mth.atan2(d2, d0) * (180F / Math.PI)) - 90.0F, 90.0F));
				this.fish.yBodyRot = this.fish.getYRot();
				this.fish.setSpeed(Mth.lerp(0.125F, this.fish.getSpeed(), (float) (this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED))));
				this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0, (double) this.fish.getSpeed() * d1 * 0.1, 0));
			} else { this.fish.setSpeed(0); }
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "controller", 5, event -> {
			if (this.isDeadOrDying()) return event.setAndContinue(RawAnimation.begin().thenPlay("death"));
			if (this.attackTimer > 0) return event.setAndContinue(RawAnimation.begin().thenPlay("chomp"));
			if (this.isInWaterOrBubble()) return event.setAndContinue(RawAnimation.begin().thenLoop("swim"));
			return event.setAndContinue(RawAnimation.begin().thenLoop("idle"));
		}));
	}

	@Override
	public boolean doHurtTarget(net.minecraft.world.entity.Entity entity) {
		this.attackTimer = 10; // Duração da animação de ataque (em ticks)
		this.level().broadcastEntityEvent(this, (byte) 4);
		return super.doHurtTarget(entity);
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 4) this.attackTimer = 10;
		else super.handleEntityEvent(id);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() { return this.cache; }
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() { return NetworkHooks.getEntitySpawningPacket(this); }
	@Override
	protected PathNavigation createNavigation(Level world) { return new WaterBoundPathNavigation(this, world); }
	@Override
	public MobType getMobType() { return MobType.WATER; }
	@Override
	public SoundEvent getHurtSound(DamageSource ds) { return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt")); }
	@Override
	public SoundEvent getDeathSound() { return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death")); }
}