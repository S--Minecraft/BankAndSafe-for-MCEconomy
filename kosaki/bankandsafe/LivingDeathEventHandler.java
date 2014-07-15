package kosaki.bankandsafe;

import mceconomy.api.MCEconomyAPI;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LivingDeathEventHandler
{
	/**
	 *Entity死亡時のイベント。敵対MOB討伐時にMPを加算､テイム状態のMOBを屠った時に減算する
	 */
	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event)
	{
		/**
		 *別のMOD等でキャンセル済みの場合はなにもしない
		 */
		if (event.isCancelable() && event.isCanceled())
		{
			return;
		}

		/**
		 *別のMOD等で不可とされた場合はなにもしない
		*/
		if (event.getResult() == Result.DENY)
		{
			return;
		}

		/**
		 *なぜかダメージソースがnullだった場合はなにもしない
		 */
		if (event.source.getSourceOfDamage() == null)
		{
			return;
		}

		/**
		 *MP増加エンチャントがあるとき
		 */
		byte moreMP=1;
		ItemStack equipItem = ((EntityPlayer)event.entityLiving).getCurrentEquippedItem();
		switch(EnchantmentHelper.getEnchantmentLevel(BankAndSafe.moreMPdropID, equipItem))
		{
		case 0: moreMP = 1; break;
		case 1: moreMP = 2; break;
		case 2: moreMP = 3; break;
		case 3: moreMP = 4; break;
		}

		/**
		 *ダメージソースがプレイヤーの場合はMP加算する
		 */
		if (event.source.getSourceOfDamage() instanceof EntityPlayerMP)
		{
			EntityPlayerMP entityPlayer = (EntityPlayerMP) event.source.getSourceOfDamage();
			if (event.entityLiving instanceof EntityZombie)
			{
				/**
				 *ゾンビを倒した場合は+2MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 2*moreMP);
			}
			else if (event.entityLiving instanceof EntityCaveSpider)
			{
				/**
				 *洞窟蜘蛛を倒した場合は+3MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 3*moreMP);
			}
			else if (event.entityLiving instanceof EntitySpider)
			{
				/**
				 *蜘蛛を倒した場合は+2MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 2*moreMP);
			}
			else if (event.entityLiving instanceof EntitySkeleton)
			{
				/**
				 *スケルトンを倒した場合は+3MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 3*moreMP);
			}
			else if (event.entityLiving instanceof EntityCreeper)
			{
				/**
				 *クリーパーを倒した場合は+4MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 4*moreMP);
			}
			else if (event.entityLiving instanceof EntityBat)
			{
				/**
				 *コウモリを倒した場合は+2MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 2*moreMP);
			}
			else if (event.entityLiving instanceof EntityDragon)
			{
				/**
				 *エンダードラゴンを倒した場合は+1000MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 1000*moreMP);
			}
			else if (event.entityLiving instanceof EntityWither)
			{
				/**
				 *ウィザーを倒した場合は+1500MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 1500*moreMP);
			}
			else if (event.entityLiving instanceof EntityEnderman)
			{
				/**
				 *エンダーマンを倒した場合は+4MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 4*moreMP);
			}
			else if (event.entityLiving instanceof EntityGhast)
			{
				/**
				 *ガストを倒した場合は+6MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 6*moreMP);
			}
			else if (event.entityLiving instanceof EntitySlime)
			{
				/**
				 *スライム･マグマキューブを倒した場合は+1MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 1*moreMP);
			}
			else if (event.entityLiving instanceof EntitySilverfish)
			{
				/**
				 *シルバーフィッシュを倒した場合は+1MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 1*moreMP);
			}
			else if (event.entityLiving instanceof EntityWitch)
			{
				/**
				 *ウィッチを倒した場合は+10MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 10*moreMP);
			}
			else if (event.entityLiving instanceof EntityVillager)
			{
				/**
				 *村人を倒した場合は-10MP(プレイヤーのMPが10未満なら0に(しないと落ちる))
				 */
				if(MCEconomyAPI.getPlayerMP(entityPlayer)<10)
				{
					MCEconomyAPI.setPlayerMP(entityPlayer, 0);
				}
				else
				{
					MCEconomyAPI.reducePlayerMP(entityPlayer, 10-(2*moreMP));
				}
			}
			else if (event.entityLiving instanceof EntityMob)
			{
				/**
				 *モンスターを倒した場合は+3MP
				 */
				MCEconomyAPI.addPlayerMP(entityPlayer, 3*moreMP);
			}
			else if(event.entityLiving instanceof EntityTameable && !event.entityLiving.getDataWatcher().getWatchableObjectString(17).isEmpty())
			{
				/**
				 *飼われている場合は-15MP(村人に同じ)
				 */
				if(MCEconomyAPI.getPlayerMP(entityPlayer)<15)
				{
					MCEconomyAPI.setPlayerMP(entityPlayer, 0);
				}
				else
				{
					MCEconomyAPI.reducePlayerMP(entityPlayer, 15-(2*moreMP));
				}
			}
		}
	}
}
