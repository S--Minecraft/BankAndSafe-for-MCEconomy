package kosaki.bankandsafe.guis;

import cpw.mods.fml.common.network.IPacketHandler;
import net.minecraft.util.ChatComponentTranslation;

/*
 * パケットの処理を行うメソッド
 */
public class GuiBlockBankButtonPacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, GuiButtonPacket packet, Player player) {
	try
	{
		EntityPlayer entityPlayer = (EntityPlayer)player;
		switch(guibutton.id)
		{
			case 1:
				//ボタン1が押されたら銀行に1000MP送金
				if(MCEconomyAPI.getPlayerMP(entityPlayer)>=1000)
				{
					MCEconomyAPI.reducePlayerMP(entityPlayer,1000);
					inBankMP =+ 1000;
				};
				break;
			case 2:
				//ボタン2が押されたら銀行に10000MP送金
				if(MCEconomyAPI.getPlayerMP(entityPlayer)>=10000)
				{
					MCEconomyAPI.reducePlayerMP(entityPlayer,10000);
					inBankMP =+ 10000;
				};
				break;
			case 3:
				//ボタン3が押されたら銀行から1000MP出金
				if(inBankMP>=1000)
				{
					MCEconomyAPI.addPlayerMP(entityPlayer,1000);
					inBankMP =- 1000;
				};
				break;
			case 4:
				//ボタン4が押されたら銀行から10000MP出金
				if(inBankMP>=10000)
				{
					MCEconomyAPI.addPlayerMP(entityPlayer,10000);
					inBankMP =- 10000;
				};
				break;
		}
		return null;
	}
}