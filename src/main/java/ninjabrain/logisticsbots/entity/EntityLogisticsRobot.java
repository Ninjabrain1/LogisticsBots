package ninjabrain.logisticsbots.entity;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityLogisticsRobot extends Entity {
	
	private static final DataParameter<ItemStack> INVENTORY = EntityDataManager.<ItemStack>createKey(EntityLogisticsRobot.class, DataSerializers.ITEM_STACK);
	
	private static final String TAG_INVENTORY = "inventoryStack";
	
	public EntityLogisticsRobot(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void entityInit() {
		// TODO create custom IItemHandler
		dataManager.register(INVENTORY, ItemStack.EMPTY);
		setSize(0.3f, 0.4f);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (!world.isRemote)
			this.rotationYaw++;
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		ItemStack loadedStack = new ItemStack(compound.getCompoundTag(TAG_INVENTORY));
		setInventoryStack(loadedStack);
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		System.out.println(getInventoryStack().serializeNBT());
		compound.setTag(TAG_INVENTORY, getInventoryStack().serializeNBT());
	}
	
	/**
	 * @return The ItemStack this robot is carrying. Returns ItemStack.EMPTY if the
	 * stack is empty.
	 */
	public ItemStack getInventoryStack() {
		// TODO improve performance
		return dataManager.get(INVENTORY);
	}
	
	private void setInventoryStack(ItemStack itemStack) {
		dataManager.set(INVENTORY, itemStack);
	}
	
	/**
	 * Picks up as many items at possible from the given stack.
	 * 
	 * @param stack
	 * ItemStack to pick up
	 * @return The remaining stack that was not picked up
	 */
	public ItemStack pickUpStack(ItemStack stack) {
		// TODO pick up itemsstacks of the same item it already carries
//		ItemHandlerHelper.canItemStacksStack(stack, inventory);
		ItemStack inventory = getInventoryStack();
		if (inventory.isEmpty()) {
			setInventoryStack(stack);
			return ItemStack.EMPTY;
		}
		return stack;
	}
	
	/**
	 * @return true if this robot is carrying any items, false otherwise
	 */
	public boolean isCarryingSomething() {
		// TODO improve performance
		return !getInventoryStack().isEmpty();
	}
	
}
