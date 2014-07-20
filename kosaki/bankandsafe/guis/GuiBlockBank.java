package kosaki.bankandsafe.guis;

import kosaki.bankandsafe.blocks.BlockBank;
import mceconomy.api.MCEconomyAPI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBlockBank extends GuiContainer
{
	private static final ResourceLocation guiTextures = new ResourceLocation("bankandsafe", "textures/guis/guiBank.png");

	public GuiBlockBank(EntityPlayer player, World world, int x, int y, int z)
	{
		super(new ContainerBlockBank(player, world, x, y, z));
		this.xSize = 176;
		this.ySize = 152;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		//文字の部分
		fontRenderer.drawString("container.bank", 83, 15, 0x404040);
		fontRenderer.drawString(playerMPString, 20, 41, 0x999999);
		fontRenderer.drawString(inBankMPString, 105, 80, 0x999999);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		//GUI画像の位置
		this.mc.getTextureManager().bindTexture(guiTextures);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int xStart = width - xSize >> 1;
		int yStart = height - ySize >> 1;
		drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}

	static ResourceLocation textureForButton()
    {
        return guiTextures;
    }

	//ボタン追加
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		//make buttons
		//id, x, y, width, height, text
		//buttonList.add(new GuiButton(GuiButton100MPBank));
		buttonList.add(new GuiButton(1, 155, 160, 18, 18,"+1000"));
		buttonList.add(new GuiButton(2, 175, 160, 18, 18,"+10000"));
		buttonList.add(new GuiButton(3, 232, 160, 18, 18,"-1000"));
		buttonList.add(new GuiButton(4, 252, 160, 18, 18,"-10000"));
	}
	protected void actionPerformed(GuiButton guibutton)
	{
		BankAndSafe.packetDispatcher.sendToServer(new GuiButtonPacket(guiButton.id));
	}

	/**
	 * 内部保存部分
	 */
	protected int inBankMP = 0;
	protected int playerMP = 0/*MCEconomyAPI.getPlayerMP(entityPlayer)*/;
	protected String inBankMPString = String.valueOf(inBankMP);
	protected String playerMPString = String.valueOf(playerMP);
}