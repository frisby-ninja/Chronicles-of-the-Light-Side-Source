package net.ninjaworks.chroniclesofthelightside.events;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.ninjaworks.chroniclesofthelightside.chroniclesofthelightside;

@Mod.EventBusSubscriber(modid = chroniclesofthelightside.MOD_ID)
public class ModEvents {

@SubscribeEvent
    public static void setEntityOnFireWhenHitWithNetheriteSword(LivingDamageEvent event) {
if(!event.getEntity().level.isClientSide()) {
    if(event.getSource().getDirectEntity() instanceof Player) {
        Player player = ((Player) event.getSource().getDirectEntity());
        if(player.getMainHandItem().getItem() == Items.NETHERITE_SWORD)  {
            event.getEntityLiving().setSecondsOnFire(2);
        }
      }
     }
    }

  }
