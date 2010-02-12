/*
 * dCompetencesFactory.java
 *
 * Created on 28 novembre 2007, 13:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package bbos.Match.Model.Competences;

import bbos.Match.Model.Competences.Agility.dcCatch;
import bbos.Match.Model.Competences.Agility.dcDivingCatch;
import bbos.Match.Model.Competences.Agility.dcDivingTackle;
import bbos.Match.Model.Competences.Agility.dcDodge;
import bbos.Match.Model.Competences.Agility.dcJumpUp;
import bbos.Match.Model.Competences.Agility.dcLeap;
import bbos.Match.Model.Competences.Agility.dcSideStep;
import bbos.Match.Model.Competences.Agility.dcSneakyGits;
import bbos.Match.Model.Competences.Agility.dcSprint;
import bbos.Match.Model.Competences.Agility.dcSureFeet;
import bbos.Match.Model.Competences.Attributes.dcAgility;
import bbos.Match.Model.Competences.Attributes.dcArmor;
import bbos.Match.Model.Competences.Attributes.dcMovement;
import bbos.Match.Model.Competences.Attributes.dcStrength;
import bbos.Match.Model.Competences.Extraordinary.dcAlwaysHungry;
import bbos.Match.Model.Competences.Extraordinary.dcAnimosity;
import bbos.Match.Model.Competences.Extraordinary.dcBallAndChain;
import bbos.Match.Model.Competences.Extraordinary.dcBloodLust;
import bbos.Match.Model.Competences.Extraordinary.dcBombardier;
import bbos.Match.Model.Competences.Extraordinary.dcBoneHead;
import bbos.Match.Model.Competences.Extraordinary.dcChainsaw;
import bbos.Match.Model.Competences.Extraordinary.dcDecay;
import bbos.Match.Model.Competences.Extraordinary.dcFanFavourite;
import bbos.Match.Model.Competences.Extraordinary.dcHypnoticGaze;
import bbos.Match.Model.Competences.Extraordinary.dcLoner;
import bbos.Match.Model.Competences.Extraordinary.dcNoHand;
import bbos.Match.Model.Competences.Extraordinary.dcNurgleRot;
import bbos.Match.Model.Competences.Extraordinary.dcReallyStupid;
import bbos.Match.Model.Competences.Extraordinary.dcRegeneration;
import bbos.Match.Model.Competences.Extraordinary.dcRightStuff;
import bbos.Match.Model.Competences.Extraordinary.dcSecretWeapon;
import bbos.Match.Model.Competences.Extraordinary.dcStab;
import bbos.Match.Model.Competences.Extraordinary.dcStakes;
import bbos.Match.Model.Competences.Extraordinary.dcStunty;
import bbos.Match.Model.Competences.Extraordinary.dcTakeRoot;
import bbos.Match.Model.Competences.Extraordinary.dcTitchy;
import bbos.Match.Model.Competences.Extraordinary.dcWildAnimal;
import bbos.Match.Model.Competences.General.dcBlock;
import bbos.Match.Model.Competences.General.dcDauntless;
import bbos.Match.Model.Competences.General.dcDirtyPlayer;
import bbos.Match.Model.Competences.General.dcFend;
import bbos.Match.Model.Competences.General.dcFrenzy;
import bbos.Match.Model.Competences.General.dcKick;
import bbos.Match.Model.Competences.General.dcKickOffReturn;
import bbos.Match.Model.Competences.General.dcPassBlock;
import bbos.Match.Model.Competences.General.dcPro;
import bbos.Match.Model.Competences.General.dcShadowing;
import bbos.Match.Model.Competences.General.dcStripBall;
import bbos.Match.Model.Competences.General.dcSureHands;
import bbos.Match.Model.Competences.General.dcTackle;
import bbos.Match.Model.Competences.General.dcWrestle;
import bbos.Match.Model.Competences.Mutation.dcBigHand;
import bbos.Match.Model.Competences.Mutation.dcClaws;
import bbos.Match.Model.Competences.Mutation.dcDisturbingPresence;
import bbos.Match.Model.Competences.Mutation.dcExtraArm;
import bbos.Match.Model.Competences.Mutation.dcFoulAppearance;
import bbos.Match.Model.Competences.Mutation.dcHorns;
import bbos.Match.Model.Competences.Mutation.dcPrehensileTail;
import bbos.Match.Model.Competences.Mutation.dcTentacles;
import bbos.Match.Model.Competences.Mutation.dcTwoHeads;
import bbos.Match.Model.Competences.Mutation.dcVeryLongLegs;
import bbos.Match.Model.Competences.Pass.dcAccurate;
import bbos.Match.Model.Competences.Pass.dcHailMaryPass;
import bbos.Match.Model.Competences.Pass.dcLeader;
import bbos.Match.Model.Competences.Pass.dcNervesOfSteel;
import bbos.Match.Model.Competences.Pass.dcPass;
import bbos.Match.Model.Competences.Pass.dcSafeThrow;
import bbos.Match.Model.Competences.Strength.dcBreakTackle;
import bbos.Match.Model.Competences.Strength.dcGrab;
import bbos.Match.Model.Competences.Strength.dcGuard;
import bbos.Match.Model.Competences.Strength.dcJuggernaut;
import bbos.Match.Model.Competences.Strength.dcMightyBlow;
import bbos.Match.Model.Competences.Strength.dcMultipleBlock;
import bbos.Match.Model.Competences.Strength.dcPilingOn;
import bbos.Match.Model.Competences.Strength.dcStandFirm;
import bbos.Match.Model.Competences.Strength.dcStrongArm;
import bbos.Match.Model.Competences.Strength.dcThickSkull;
import bbos.Match.Model.Competences.Strength.dcThrowATeamMate;
import bbos.Match.Model.dPlayer;

/**
 *
 * @author Administrateur
 */
public class dCompetencesFactory
{
    
    public static final dCompetenceType Agility=new dCompetenceType("Agility");
    public static final dCompetenceType Extraordinary=new dCompetenceType("Extraordinary");
    public static final dCompetenceType General=new dCompetenceType("General");
    public static final dCompetenceType Pass=new dCompetenceType("Pass");
    public static final dCompetenceType Mutation=new dCompetenceType("Mutation");
    public static final dCompetenceType Strength=new dCompetenceType("Strength");
    public static final dCompetenceType Attributes=new dCompetenceType("Attributes");
    
    /** Creates a new instance of dCompetencesFactory */
    public dCompetencesFactory ()
    {
    }
    
    public static dCompetence createCompetence(String name)
    {
        /* AGILITY */
        if (name.equals("Catch"))
        {
            return new dcCatch(name);
        }
        if (name.equals("Diving catch"))
        {
            return new dcDivingCatch(name);
        }
        if (name.equals("Diving tackle"))
        {
            return new dcDivingTackle(name);
        }
        if (name.equals("Dodge"))
        {
            return new dcDodge(name);
        }
        if (name.equals("Dump off"))
        {
            return new dcDivingTackle(name);
        }
        if (name.equals("Jump up"))
        {
            return new dcJumpUp(name);
        }
        if (name.equals("Leap"))
        {
            return new dcLeap(name);
        }
        if (name.equals("Side step"))
        {
            return new dcSideStep(name);
        }
        if (name.equals("Sneaky gits"))
        {
            return new dcSneakyGits(name);
        }
        if (name.equals("Sprint"))
        {
            return new dcSprint(name);
        }
        if (name.equals("Sure feet"))
        {
            return new dcSureFeet(name);
        }
        /* ATTRIBUTES */
        if (name.equals("+1 Strength"))
        {
            return new dcStrength(name);
        }
        if (name.equals("+1 Agility"))
        {
            return new dcAgility(name);
        }
        if (name.equals("+1 Armor"))
        {
            return new dcArmor(name);
        }
        if (name.equals("+1 Movement"))
        {
            return new dcMovement(name);
        }
        
         /* EXTRAORDINARY */
        if (name.equals("Always hungry"))
        {
            return new dcAlwaysHungry(name);
        }
        if (name.equals("Animosity"))
        {
            return new dcAnimosity(name);
        }
        if (name.equals("Ball and chain"))
        {
            return new dcBallAndChain(name);
        }
        if (name.equals("Blood lust"))
        {
            return new dcBloodLust(name);
        }
        if (name.equals("Bombardier"))
        {
            return new dcBombardier(name);
        }
        if (name.equals("Bone head"))
        {
            return new dcBoneHead(name);
        }
        if (name.equals("Chainsaw"))
        {
            return new dcChainsaw(name);
        }
        if (name.equals("Decay"))
        {
            return new dcDecay(name);
        }
        if (name.equals("Fan favorite"))
        {
            return new dcFanFavourite(name);
        }
        if (name.equals("Hypnotic gaze"))
        {
            return new dcHypnoticGaze(name);
        }
        if (name.equals("Loner"))
        {
            return new dcLoner(name);
        }
        if (name.equals("No hand"))
        {
            return new dcNoHand(name);
        }
        if (name.equals("Nurgle's rot"))
        {
            return new dcNurgleRot(name);
        }
        if (name.equals("Really stupid"))
        {
            return new dcReallyStupid(name);
        }
        if (name.equals("Regeneration"))
        {
            return new dcRegeneration(name);
        }
        if (name.equals("Right stuff"))
        {
            return new dcRightStuff(name);
        }
        
        if (name.equals("Secret weapon"))
        {
            return new dcSecretWeapon(name);
        }
        if (name.equals("Stab"))
        {
            return new dcStab(name);
        }
        if (name.equals("Stakes"))
        {
            return new dcStakes(name);
        }
        if (name.equals("Stunty"))
        {
            return new dcStunty(name);
        }
        if (name.equals("Take root"))
        {
            return new dcTakeRoot(name);
        }
        if (name.equals("Titchy"))
        {
            return new dcTitchy(name);
        }
        if (name.equals("Wild animal"))
        {
            return new dcWildAnimal(name);
        }
        /* GENERAL */
        if (name.equals("Block"))
        {
            return new dcBlock(name);
        }
        if (name.equals("Dauntless"))
        {
            return new dcDauntless(name);
        }
        if (name.equals("Dirty player"))
        {
            return new dcDirtyPlayer(name);
        }
        if (name.equals("Fend"))
        {
            return new dcFend(name);
        }
        if (name.equals("Frenzy"))
        {
            return new dcFrenzy(name);
        }
        if (name.equals("Kick"))
        {
            return new dcKick(name);
        }
        if (name.equals("Kick-off return"))
        {
            return new dcKickOffReturn(name);
        }
        if (name.equals("Pass block"))
        {
            return new dcPassBlock(name);
        }
        if (name.equals("Pro"))
        {
            return new dcPro(name);
        }
        if (name.equals("Shadowing"))
        {
            return new dcShadowing(name);
        }
        if (name.equals("Strip ball"))
        {
            return new dcStripBall(name);
        }
        if (name.equals("Sure hands"))
        {
            return new dcSureHands(name);
        }
        if (name.equals("Tackle"))
        {
            return new dcTackle(name);
        }
        if (name.equals("Wrestle"))
        {
            return new dcWrestle(name);
        }
        /* MUTATION */
        if (name.equals("Big hand"))
        {
            return new dcBigHand(name);
        }
        if (name.equals("Claws"))
        {
            return new dcClaws(name);
        }
        if (name.equals("Disturbing presence"))
        {
            return new dcDisturbingPresence(name);
        }
        if (name.equals("Extra arm"))
        {
            return new dcExtraArm(name);
        }
        if (name.equals("Foul appearance"))
        {
            return new dcFoulAppearance(name);
        }
        if (name.equals("Horns"))
        {
            return new dcHorns(name);
        }
        if (name.equals("Prehensile tail"))
        {
            return new dcPrehensileTail(name);
        }
        if (name.equals("Tentacles"))
        {
            return new dcTentacles(name);
        }
        if (name.equals("Two heads"))
        {
            return new dcTwoHeads(name);
        }
        if (name.equals("Very long legs"))
        {
            return new dcVeryLongLegs(name);
        }
        /* PASS */
        if (name.equals("Accurate"))
        {
            return new dcAccurate(name);
        }
        if (name.equals("Hail Mary pass"))
        {
            return new dcHailMaryPass(name);
        }
        if (name.equals("Leader"))
        {
            return new dcLeader(name);
        }
        if (name.equals("Nerves of steel"))
        {
            return new dcNervesOfSteel(name);
        }
        if (name.equals("Pass"))
        {
            return new dcPass(name);
        }
        if (name.equals("Safe throw"))
        {
            return new dcSafeThrow(name);
        }
        /* STRENGTH */
        if (name.equals("Break tackle"))
        {
            return new dcBreakTackle(name);
        }
        if (name.equals("Grab"))
        {
            return new dcGrab(name);
        }
        if (name.equals("Guard"))
        {
            return new dcGuard(name);
        }
        if (name.equals("Juggernaut"))
        {
            return new dcJuggernaut(name);
        }
         if (name.equals("Mighty blow"))
        {
            return new dcMightyBlow(name);
        }
        if (name.equals("Multiple block"))
        {
            return new dcMultipleBlock(name);
        }
        if (name.equals("Piling on"))
        {
            return new dcPilingOn(name);
        }
        if (name.equals("Stand firm"))
        {
            return new dcStandFirm(name);
        }
        if (name.equals("Strong arm"))
        {
            return new dcStrongArm(name);
        }
        if (name.equals("Thick skull"))
        {
            return new dcThickSkull(name);
        }
        if (name.equals("Throw a team mate"))
        {
            return new dcThrowATeamMate(name);
        }
        // To Do
        return null;
    }
    
    public static dCompetenceType getCompetenceType(String name)
    {
        if (name.equals("Agility"))
        {
            return Agility;
        }
         if (name.equals("Extraordinary"))
        {
            return Extraordinary;
        }
         if (name.equals("General"))
        {
            return General;
        }
         if (name.equals("Pass"))
        {
            return Pass;
        }
         if (name.equals("Mutation"))
        {
            return Mutation;
        }
         if (name.equals("Strength"))
        {
            return Strength;
        }
        if (name.equals("Attributes"))
        {
            return Attributes;
        }
        
        return null;
    }
}
