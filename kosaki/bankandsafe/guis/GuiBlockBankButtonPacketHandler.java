/*
package kosaki.bankandsafe.guis;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import mceconomy.api.MCEconomyAPI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/*
 * パケットの処理を行うメソッド
 */
/*
public class GuiBlockBankButtonPacketHandler implements IPacketHandler
{
	public void onPacketData(INetworkManager manager, GuiBlockBankButtonPacket packet, Player player) {
	try
	{
		EntityPlayer entityPlayer = (EntityPlayer)player;
		switch(GuiBlockBank.guibutton.id)
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
	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {

	}

	}
*/