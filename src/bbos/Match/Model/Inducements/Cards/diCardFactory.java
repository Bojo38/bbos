/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.Match.Model.Inducements.Cards;

import bbos.Match.Model.Inducements.*;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicAssassin;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicComeOnBoys;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicDaFreightTrain;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicDoomAndGloom;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicIAmTheGreatest;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicMindblow;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicMorleyRevenge;
import bbos.Match.Model.Inducements.Cards.DesperateMeasure.dicMysteriousOldMedecineMan;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicBlatantFoul;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicChopBlock;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicCustardPie;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicDistract;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicGreasedShoes;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicGromskullExplodingRunes;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicIllegalSubstitution;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicKickingBoots;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicPitTrap;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicSpikedBall;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicStolenPlaybook;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicTrampolineTrap;
import bbos.Match.Model.Inducements.Cards.DirtyTrick.dicWitchBrew;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicAllOutBlitz;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicBananaSkin;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicButterfingers;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicChainsaw;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicDazedAndConfused;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicDocBonesaw;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicExtraTraining;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicFanUproar;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicHurryUpOffense;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicIntensiveTraining;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicKnuttSpellOfAwesomeStrength;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicLewdManeuvers;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicLurvePotion;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicMagicHelmet;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicMiracleWorker;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicOneWithTheKicker;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicRazzleDazzle;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicRuneOfFear;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicScottScrollOfWeatherMagic;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicStiletto;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicSuitablePitch;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicTeamAnthem;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicTheFan;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicTheWall;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicUnsportsmanlikeConduct;
import bbos.Match.Model.Inducements.Cards.GoodKarma.dicWoofWoof;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicBeguilingBracer;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicBeltOfInvulnerability;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicFawndough;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicForceShield;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicGiktaStrength;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicGlovesOfHolding;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicGoodOldMagicCodpiece;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicInertiaDampner;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicLuckyCharm;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicMagicGlovesOfJackLongarm;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicRabbitFoot;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicRingOfTeleportation;
import bbos.Match.Model.Inducements.Cards.MagicItem.dicWandOfSmashing;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicBadyearGit;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicBallClone;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicEclipse;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicFanaticInvasion;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicFriendlyFans;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicHeckler;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicHometownFans;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicIncoming;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicJohnnyWaterboy;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicRogueWizard;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicRowdyFans;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicSprinklerMalfunction;
import bbos.Match.Model.Inducements.Cards.MiscMayhem.dicThatBabe;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicBadHabits;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicBallista;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicBlackMail;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicBuzzing;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicDuhWhereAmI;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicEgoTrip;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicGetEmLads;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicGimmeThat;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicIronMan;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicKidGloves;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicKnuckledusters;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicMagicSponge;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicMine;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicNotSoSecretWeapon;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicOrcidasSponsorship;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicRakarthCurseOfPettySpite;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicTacklingMachine;
import bbos.Match.Model.Inducements.Cards.RandomEvent.dicZap;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicComeToPapa;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicDoggedDefense;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicFleaFlicker;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicFumblerooski;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicGoingTheExtraMile;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicHeroicLeap;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicNewBlockingScheme;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicOptionPlay;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicPerfectKick;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicPunt;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicSpectacularCatch;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicSuicideBlitz;
import bbos.Match.Model.Inducements.Cards.SpecialPlay.dicWakeUpCall;
import bbos.Tools.bbTool;

/**
 *
 * @author root
 */
public class diCardFactory {

    public final static int C_MAGIC_ITEM = 1;
    public final static int C_MISC_MAYHEM = 2;
    public final static int C_SPECIAL_PLAY = 3;
    public final static int C_DIRTY_TRICK = 4;
    public final static int C_GOOD_KARMA = 5;
    public final static int C_RANDOM_EVENT = 6;
    public final static int C_DESPERATE_MEASURE = 7;

    public static diCard createCard(int cardType) {
        diCard card = null;

        int cardid;

        switch (cardType) {
            case 1:
                /* 13 cards - Magic Item */
                cardid = bbTool.getdN(13);
                switch (cardid) {
                    case 1:
                        card = new dicBeguilingBracer();
                        break;
                    case 2:
                        card = new dicBeltOfInvulnerability();
                        break;
                    case 3:
                        card = new dicFawndough();
                        break;
                    case 4:
                        card = new dicForceShield();
                        break;
                    case 5:
                        card = new dicGiktaStrength();
                        break;
                    case 6:
                        card = new dicGlovesOfHolding();
                        break;
                    case 7:
                        card = new dicGoodOldMagicCodpiece();
                        break;
                    case 8:
                        card = new dicInertiaDampner();
                        break;
                    case 9:
                        card = new dicLuckyCharm();
                        break;
                    case 10:
                        card = new dicMagicGlovesOfJackLongarm();
                        break;
                    case 11:
                        card = new dicRabbitFoot();
                        break;
                    case 12:
                        card = new dicRingOfTeleportation();
                        break;
                    case 13:
                        card = new dicWandOfSmashing();
                        break;
                }
                break;
            case 2:
                /* 13 cards - Misc Mayhem */
                cardid = bbTool.getdN(13);
                switch (cardid) {
                    case 1:
                        card = new dicBadyearGit();
                        break;
                    case 2:
                        card = new dicBallClone();
                        break;
                    case 3:
                        card = new dicEclipse();
                        break;
                    case 4:
                        card = new dicFanaticInvasion();
                        break;
                    case 5:
                        card = new dicFriendlyFans();
                        break;
                    case 6:
                        card = new dicHeckler();
                        break;
                    case 7:
                        card = new dicHometownFans();
                        break;
                    case 8:
                        card = new dicIncoming();
                        break;
                    case 9:
                        card = new dicJohnnyWaterboy();
                        break;
                    case 10:
                        card = new dicRogueWizard();
                        break;
                    case 11:
                        card = new dicRowdyFans();
                        break;
                    case 12:
                        card = new dicSprinklerMalfunction();
                        break;
                    case 13:
                        card = new dicThatBabe();
                        break;
                }
                break;
            case 3:
                cardid = bbTool.getdN(13);
                /* 13 cards - Special Play*/
                switch (cardid) {
                    case 1:
                        card = new dicComeToPapa();
                        break;
                    case 2:
                        card = new dicDoggedDefense();
                        break;
                    case 3:
                        card = new dicFleaFlicker();
                        break;
                    case 4:
                        card = new dicFumblerooski();
                        break;
                    case 5:
                        card = new dicGoingTheExtraMile();
                        break;
                    case 6:
                        card = new dicHeroicLeap();
                        break;
                    case 7:
                        card = new dicNewBlockingScheme();
                        break;
                    case 8:
                        card = new dicOptionPlay();
                        break;
                    case 9:
                        card = new dicPerfectKick();
                        break;
                    case 10:
                        card = new dicPunt();
                        break;
                    case 11:
                        card = new dicSpectacularCatch();
                        break;
                    case 12:
                        card = new dicSuicideBlitz();
                        break;
                    case 13:
                        card = new dicWakeUpCall();
                        break;
                }
                break;
            case 4:
                /* 13 cards - Dirty Trick*/
                cardid = bbTool.getdN(13);
                switch (cardid) {
                    case 1:
                        card = new dicBlatantFoul();
                        break;
                    case 2:
                        card = new dicChopBlock();
                        break;
                    case 3:
                        card = new dicCustardPie();
                        break;
                    case 4:
                        card = new dicDistract();
                        break;
                    case 5:
                        card = new dicGreasedShoes();
                        break;
                    case 6:
                        card = new dicGromskullExplodingRunes();
                        break;
                    case 7:
                        card = new dicIllegalSubstitution();
                        break;
                    case 8:
                        card = new dicKickingBoots();
                        break;
                    case 9:
                        card = new dicPitTrap();
                        break;
                    case 10:
                        card = new dicSpikedBall();
                        break;
                    case 11:
                        card = new dicStolenPlaybook();
                        break;
                    case 12:
                        card = new dicTrampolineTrap();
                        break;
                    case 13:
                        card = new dicWitchBrew();
                        break;
                }
                break;
            case 5:
                /* 26 cards - Good Karma */
                cardid = bbTool.getdN(26);
                switch (cardid) {
                    case 1:
                        card = new dicAllOutBlitz();
                        break;
                    case 2:
                        card = new dicBananaSkin();
                        break;
                    case 3:
                        card = new dicButterfingers();
                        break;
                    case 4:
                        card = new dicChainsaw();
                        break;
                    case 5:
                        card = new dicDazedAndConfused();
                        break;
                    case 6:
                        card = new dicDocBonesaw();
                        break;
                    case 7:
                        card = new dicExtraTraining();
                        break;
                    case 8:
                        card = new dicFanUproar();
                        break;
                    case 9:
                        card = new dicHurryUpOffense();
                        break;
                    case 10:
                        card = new dicIntensiveTraining();
                        break;
                    case 11:
                        card = new dicKnuttSpellOfAwesomeStrength();
                        break;
                    case 12:
                        card = new dicLewdManeuvers();
                        break;
                    case 13:
                        card = new dicLurvePotion();
                        break;
                    case 14:
                        card = new dicMagicHelmet();
                        break;
                    case 15:
                        card = new dicMiracleWorker();
                        break;
                    case 16:
                        card = new dicOneWithTheKicker();
                        break;
                    case 17:
                        card = new dicRazzleDazzle();
                        break;
                    case 18:
                        card = new dicRuneOfFear();
                        break;
                    case 19:
                        card = new dicScottScrollOfWeatherMagic();
                        break;
                    case 20:
                        card = new dicStiletto();
                        break;
                    case 21:
                        card = new dicSuitablePitch();
                        break;
                    case 22:
                        card = new dicTeamAnthem();
                        break;
                    case 23:
                        card = new dicTheFan();
                        break;
                    case 24:
                        card = new dicTheWall();
                        break;
                    case 25:
                        card = new dicUnsportsmanlikeConduct();
                        break;
                    case 26:
                        card = new dicWoofWoof();
                        break;
                }
                break;
            case 6:
                /* 18 cards - Random event*/
                cardid = bbTool.getdN(18);
                switch (cardid) {
                    case 1:
                        card = new dicBadHabits();
                        break;
                    case 2:
                        card = new dicBallista();
                        break;
                    case 3:
                        card = new dicBlackMail();
                        break;
                    case 4:
                        card = new dicBuzzing();
                        break;
                    case 5:
                        card = new dicDuhWhereAmI();
                        break;
                    case 6:
                        card = new dicEgoTrip();
                        break;
                    case 7:
                        card = new dicGetEmLads();
                        break;
                    case 8:
                        card = new dicGimmeThat();
                        break;
                    case 9:
                        card = new dicIronMan();
                        break;
                    case 10:
                        card = new dicKidGloves();
                        break;
                    case 11:
                        card = new dicKnuckledusters();
                        break;
                    case 12:
                        card = new dicMine();
                        break;
                    case 13:
                        card = new dicNotSoSecretWeapon();
                        break;
                    case 14:
                        card = new dicOrcidasSponsorship();
                        break;
                    case 15:
                        card = new dicRakarthCurseOfPettySpite();
                        break;
                    case 16:
                        card = new dicTacklingMachine();
                        break;
                    case 17:
                        card = new dicZap();
                        break;
                    case 18:
                        card = new dicMagicSponge();
                        break;
                }
                break;
            case 7:
                /* 8 cards - Desperate measure*/
                cardid = bbTool.getdN(8);
                switch (cardid) {
                    case 1:
                        card = new dicAssassin();
                        break;
                    case 2:
                        card = new dicComeOnBoys();
                        break;
                    case 3:
                        card = new dicDaFreightTrain();
                        break;
                    case 4:
                        card = new dicDoomAndGloom();
                        break;
                    case 5:
                        card = new dicIAmTheGreatest();
                        break;
                    case 6:
                        card = new dicMindblow();
                        break;
                    case 7:
                        card = new dicMorleyRevenge();
                        break;
                    case 8:
                        card = new dicMysteriousOldMedecineMan();
                        break;
                }
                break;
        }

        return card;
    }
}
