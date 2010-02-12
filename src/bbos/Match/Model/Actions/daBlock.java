/*
 * daBlock.java
 *
 * Created on 28 novembre 2007, 18:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package bbos.Match.Model.Actions;

import bbos.Tools.bbTool;
import bbos.Match.Model.Roll.drInjury;
import bbos.Match.Model.Roll.drArmor;
import bbos.Match.Model.dPlayer;
import bbos.Match.Model.dSquare;
import bbos.*;
import bbos.Match.Model.Roll.Agility.draGetBall;
import bbos.Match.Model.Roll.drScatter;
import bbos.Match.Model.rmiMatch;
import bbos.Match.Model.rmiPlayer;
import bbos.Match.Model.rmiTeam;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrateur
 */
public class daBlock extends dAction {

    rmiMatch _model;
    public int _chosenResult;
    public static final int STEP_CHOOSE_OPPONENT = 0;
    public static final int STEP_ROLL_DICE = 1;
    public static final int STEP_ATTACKER_CHOOSE_DICE = 5;
    public static final int STEP_DEFENDER_CHOOSE_DICE = 6;
    public static final int STEP_CHOOSE_SQUARE = 2;
    public static final int STEP_CHOSEN_SQUARE = 3;
    public static final int STEP_END = 4;
    public static final int DICE_ONE = 1;
    public static final int DICE_TWO_FOR = 2;
    public static final int DICE_TWO_AGAINST = 3;
    public static final int DICE_THREE_FOR = 4;
    public static final int DICE_THREE_AGAINST = 5;
    public static final int DICE_NO = 0;
    public static final int DICE_SKULL = 1;
    public static final int DICE_POW_SKULL = 2;
    public static final int DICE_PUSH1 = 3;
    public static final int DICE_PUSH_BALL_DOWN = 7;
    public static final int DICE_BOTH_DOWN_NOT_ROLL=8;
    public static final int DICE_PUSH2 = 4;
    public static final int DICE_POW_DODGE = 5;
    public static final int DICE_POW = 6;
    public static final int RESULT_ATTACKER_DOWN = 1;
    public static final int RESULT_DEFENDER_DOWN = 2;
    public static final int RESULT_DEFENDER_PUSHED = 3;
    public static final int RESULT_BOTH_DOWN = 4;
    public static final int RESULT_DEFENDER_PUSHED_DOWN = 5;
    public static final int RESULT_NO_CHANGE = 6;
    int _dices;
    int _dice1;
    int _dice2;
    int _dice3;
    int _chosenDice = 0;
    rmiPlayer _attacker;
    rmiPlayer _defender = null;
    Vector _pushedPlayer;
    Vector _pushedSquares;
    dSquare _defenderSquare;
    rmiTeam _opponent;
    rmiTeam _myTeam;
    Vector _opponentPlayers;
    Vector _myPlayers;
    boolean _isChallenger;
    protected static ImageIcon _icon = new ImageIcon(dAction.class.getResource("/resources/images/actions/block.gif"));

    public ImageIcon getIcon() {
        return _icon;
    }

    public int getDices() {
        return _dices;
    }

    public int getDice1() {
        return _dice1;
    }

    public int getDice2() {
        return _dice2;
    }

    public int getDice3() {
        return _dice3;
    }

    public void SelectDice(int value) {

        try {
            if ((_step == STEP_ATTACKER_CHOOSE_DICE) || (_step == STEP_DEFENDER_CHOOSE_DICE)) {
                _chosenDice = value;
                int diceValue = 0;
                switch (value) {
                    case 1:
                        diceValue = _dice1;
                        break;
                    case 2:
                        diceValue = _dice2;
                    case 3:
                        diceValue = _dice3;
                }
                int result = getResult(diceValue);
                if ((result == RESULT_DEFENDER_PUSHED) || (result == RESULT_DEFENDER_PUSHED_DOWN)) {
                    /**
                     * Si pas de case possibles :=> la touche
                     * */
                    Vector squares = _defender.getPushedSquares(_attacker.getX(), _attacker.getY());
                    /**
                     * en touche
                     */
                    if (squares.size() == 0) {
                        _defender.setOnPitch(false);
                        drInjury injury = new drInjury();
                        int inj = injury.rollDices();
                        injury.applyEffects(inj, _defender);
                        _pushedPlayer.remove(_defender);

                    } else {
                        /* sinon
                         */
                        _step = STEP_CHOOSE_SQUARE;
                        selectSquares();
                    }
                } else {
                    _step = STEP_END;

                    _model.resetSquareBlock();
                    _model.resetSquareFoul();
                    _model.resetSquareMove();
                    _model.resetSquarePass();

                    _attacker.hasPlayed(true);
                    _attacker.isActive(false);
                    _attacker.isPlaying(false);
                }
                _model.setLeftRefresh(true);
                _model.setRightRefresh(true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /** Creates a new instance of daBlock */
    public daBlock(rmiMatch model, rmiPlayer player, rmiTeam opponent, rmiTeam myTeam, Vector opponentPlayers, Vector myPlayers, boolean isChallenger) {
        super(C_BLOCK);
        _model = model;
        _attacker = player;
        _dices = 0;
        _step = 0;
        _opponent = opponent;
        _myTeam = myTeam;
        _isChallenger = isChallenger;
        _opponentPlayers = opponentPlayers;
        _myPlayers = myPlayers;
    }

    public void selectSquares() {
        try {
            if (_step == STEP_CHOOSE_OPPONENT) {
                Vector squares = _attacker.getAroundSquares();
                for (int i = 0; i < squares.size(); i++) {
                    dSquare s = (dSquare) squares.get(i);
                    int dices = DICE_NO;
                    dices = _attacker.getDiceForBlock(s);
                    if (dices != DICE_NO) {
                        _model.setSquareState(s, dSquare.C_SQUARE_OPPONENT_TO_BLOCK);
                        _model.setBlockDiceNumber(s, dices);
                    }
                }
            }

            if (_step == STEP_CHOOSE_SQUARE) {
                if (_defender != null) {
                    Vector squares = _defender.getPushedSquares(_attacker.getX(), _attacker.getY());
                    for (int i = 0; i < squares.size(); i++) {
                        dSquare s = (dSquare) squares.get(i);
                        _model.setSquareState(s, dSquare.C_SQUARE_PUSH_ZONE);
                    }
                }
            }

        } catch (RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    public void resetStep() {
        try {
            _model.resetSquareBlock();

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
            _step = 0;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void preStep() {
        this.selectSquares();
        try {
            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void step(dSquare sq) {

        try {
            dSquare s = _model.getSquares().getSquare(sq.getX(), sq.getY());
            if (_step == STEP_CHOOSE_OPPONENT) {
                if (s.getState() == dSquare.C_SQUARE_OPPONENT_TO_BLOCK) {
                    int number = _opponent.getPlayerNumber(s.getX(), s.getY());
                    if (number >= 0) {
                        _defender = (rmiPlayer) _opponentPlayers.get(number);
                        _step = STEP_ROLL_DICE;
                        _attacker.isPlaying(true);
                    }
                }
            }

            if (_step == STEP_ROLL_DICE) {
                _dices = _attacker.getDiceForBlock(s);
                switch (_dices) {
                    case DICE_ONE:
                        _dice1 = bbTool.getd6();
                        _step = STEP_ATTACKER_CHOOSE_DICE;
                        break;
                    case DICE_TWO_FOR:
                        _dice1 = bbTool.getd6();
                        _dice2 = bbTool.getd6();
                        _step = STEP_ATTACKER_CHOOSE_DICE;
                        break;
                    case DICE_TWO_AGAINST:
                        _step = STEP_DEFENDER_CHOOSE_DICE;
                        _dice1 = bbTool.getd6();
                        _dice2 = bbTool.getd6();
                        break;
                    case DICE_THREE_FOR:
                        _step = STEP_ATTACKER_CHOOSE_DICE;
                        _dice1 = bbTool.getd6();
                        _dice2 = bbTool.getd6();
                        _dice3 = bbTool.getd6();
                        break;
                    case DICE_THREE_AGAINST:
                        _step = STEP_DEFENDER_CHOOSE_DICE;
                        _dice1 = bbTool.getd6();
                        _dice2 = bbTool.getd6();
                        _dice3 = bbTool.getd6();
                        break;
                }

                /**
                 * Attente du choix des dés
                 */
                if (_step == STEP_DEFENDER_CHOOSE_DICE) {
                /**
                 * Si besoin, relancer avant le choix adverse
                 */
                }

                int chooser = 2;
                if (_isChallenger) {
                    chooser = 1;
                }

                _model.waitForDiceChoice(chooser, _dices, _dice1, _dice2, _dice3);

            }

            if (_step == STEP_CHOOSE_SQUARE) {
                if (s.getState() == dSquare.C_SQUARE_PUSH_ZONE) {
                    _pushedPlayer = new Vector();
                    _pushedPlayer.add(_defender);
                    _defenderSquare = new dSquare(_defender.getX(), _defender.getY());
                    _pushedSquares = new Vector();
                    _step = STEP_CHOSEN_SQUARE;
                }

                if (_step == STEP_CHOSEN_SQUARE) {
                    if (_model.isAPlayer(s)) {
                        _pushedSquares.add(s);
                        int i = _opponent.getPlayerNumber(s.getX(), s.getY());
                        rmiPlayer pushed = null;
                        if (i >= 0) {
                            pushed = (rmiPlayer) _opponentPlayers.get(i);
                            _pushedPlayer.add(pushed);
                        } else {
                            i = _myTeam.getPlayerNumber(s.getX(), s.getY());
                            pushed = (rmiPlayer) _myPlayers.get(i);
                            _pushedPlayer.add(pushed);
                        }

                        _model.resetSquareBlock();
                        _model.resetSquareMove();

                        if (pushed != null) {
                            rmiPlayer source = (rmiPlayer) _pushedPlayer.get(_pushedPlayer.size() - 2);
                            Vector squares = pushed.getPushedSquares(source.getX(), source.getY());
                            for (i = 0; i < squares.size(); i++) {
                                dSquare tmp = (dSquare) squares.get(i);
                                _model.setSquareState(tmp, dSquare.C_SQUARE_PUSH_ZONE);

                                dSquare s_ball = _model.getBallSquare();
                                if ((s_ball.getX() == tmp.getX()) && (s_ball.getY() == tmp.getY())) {

                                }
                            }
                            /**
                             * en touche
                             */
                            if (squares.size() == 0) {
                                pushed.setOnPitch(false);
                                drInjury injury = new drInjury();
                                int inj = injury.rollDices();
                                injury.applyEffects(inj, pushed);
                                _pushedPlayer.remove(pushed);
                            }
                        }

                    }

                    if (!_model.isAPlayer(s)) {
                        if ((_pushedPlayer != null) && (_pushedSquares != null)) {
                            _pushedSquares.add(s);
                            for (int i = 0; i < _pushedPlayer.size(); i++) {
                                rmiPlayer player = (rmiPlayer) _pushedPlayer.get(i);
                                dSquare square = (dSquare) _pushedSquares.get(i);
                                player.setX(square.getX());
                                player.setY(square.getY());
                            }
                        }

                        if (_attacker.mustFollow()) {
                            _attacker.setX(_defenderSquare.getX());
                            _attacker.setY(_defenderSquare.getY());
                        } else {
                            int question = JOptionPane.showConfirmDialog(null, "Do you want to follow ?", "Follow ?", JOptionPane.YES_NO_OPTION);
                            if (question == JOptionPane.YES_OPTION) {
                                _attacker.setX(_defenderSquare.getX());
                                _attacker.setY(_defenderSquare.getY());
                            }
                        }
                        int diceValue = 0;
                        switch (_chosenDice) {
                            case 1:
                                diceValue = _dice1;
                                break;
                            case 2:
                                diceValue = _dice2;
                            case 3:
                                diceValue = _dice3;
                            }
                        int result = getResult(diceValue);
                        applyResult(result);

                        _step = STEP_END;

                        _model.resetSquareBlock();
                        _model.resetSquareFoul();
                        _model.resetSquareMove();
                        _model.resetSquarePass();

                        _attacker.hasPlayed(true);
                        _attacker.isActive(false);
                        _attacker.isPlaying(false);
                    }
                }
            }

            _model.setLeftRefresh(true);
            _model.setRightRefresh(true);

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void postStep() {

    }

    protected int getResult(int dice) {
        switch (dice) {
            case DICE_SKULL:
                return RESULT_ATTACKER_DOWN;
            case DICE_POW_SKULL:
                return RESULT_BOTH_DOWN;
            case DICE_PUSH1:
                return RESULT_DEFENDER_PUSHED;
            case DICE_PUSH2:
                return RESULT_DEFENDER_PUSHED;
            case DICE_POW_DODGE:
                return RESULT_DEFENDER_PUSHED_DOWN;
            case DICE_POW:
                return RESULT_DEFENDER_PUSHED_DOWN;
        }
        return 0;
    }

    protected void applyResult(int result) {
        int ar;
        int inj;
        drArmor armor = new drArmor();
        drInjury injury = new drInjury();
        try {
            switch (result) {
                case RESULT_ATTACKER_DOWN:
                    ar = armor.rollDices();
                    if (armor.isSuccess(_attacker, ar)) {
                        inj = injury.rollDices();
                        injury.applyEffects(inj, _attacker);
                    } else {
                        _attacker.setState(dPlayer.C_STATE_PRONE);
                    }
                    if (_attacker.hasBall()) {
                        _attacker.setBall(false);
                        scatterBall(new dSquare(_attacker.getX(), _attacker.getY()));
                    }
                    _model.turnover(true);
                    break;
                case RESULT_BOTH_DOWN:
                    ar = armor.rollDices();
                    if (armor.isSuccess(_defender, ar)) {
                        inj = injury.rollDices();
                        injury.applyEffects(inj, _defender);
                    } else {
                        _defender.setState(dPlayer.C_STATE_PRONE);
                    }
                    ar = armor.rollDices();
                    if (armor.isSuccess(_attacker, ar)) {
                        inj = injury.rollDices();
                        injury.applyEffects(inj, _attacker);
                    } else {
                        _attacker.setState(dPlayer.C_STATE_PRONE);
                    }

                    if (_attacker.hasBall()) {
                        _attacker.setBall(false);
                        scatterBall(new dSquare(_attacker.getX(), _attacker.getY()));
                    }

                    if (_defender.hasBall()) {
                        _defender.setBall(false);
                        scatterBall(new dSquare(_defender.getX(), _defender.getY()));
                    }
                    _model.turnover(true);
                    break;
                case RESULT_DEFENDER_DOWN:
                    ar = armor.rollDices();
                    if (armor.isSuccess(_defender, ar)) {
                        inj = injury.rollDices();
                        injury.applyEffects(inj, _defender);
                    } else {
                        _defender.setState(dPlayer.C_STATE_PRONE);
                    }
                    if (_defender.hasBall()) {
                        _defender.setBall(false);
                        scatterBall(new dSquare(_defender.getX(), _defender.getY()));
                    }

                    break;
                case RESULT_DEFENDER_PUSHED_DOWN:
                    ar = armor.rollDices();
                    if (armor.isSuccess(_defender, ar)) {
                        inj = injury.rollDices();
                        injury.applyEffects(inj, _defender);
                    } else {
                        _defender.setState(dPlayer.C_STATE_PRONE);
                    }
                    if (_defender.hasBall()) {
                        _defender.setBall(false);
                        scatterBall(new dSquare(_defender.getX(), _defender.getY()));
                    }

                    break;
                case RESULT_DEFENDER_PUSHED:
                    break;
                case RESULT_NO_CHANGE:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return "Block";
    }
    public static ImageIcon I_DICE_SKULL = null;
    public static ImageIcon I_DICE_POW_SKULL = null;
    public static ImageIcon I_DICE_PUSH_1 = null;
    public static ImageIcon I_DICE_PUSH_2 = null;
    public static ImageIcon I_DICE_POW_DODGE = null;
    public static ImageIcon I_DICE_POW = null;

    public static ImageIcon getBlockDiceIcon(int value, boolean isChallenger) {
        switch (value) {
            case 1:
                if (I_DICE_SKULL == null) {
                    if (isChallenger) {
                        I_DICE_SKULL = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/home-1.png"));
                    } else {
                        I_DICE_SKULL = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/away-1.png"));
                    }
                }
                return I_DICE_SKULL;
            case 2:
                if (I_DICE_POW_SKULL == null) {
                    if (isChallenger) {
                        I_DICE_POW_SKULL = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/home-2.png"));
                    } else {
                        I_DICE_POW_SKULL = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/away-2.png"));
                    }
                }
                return I_DICE_POW_SKULL;
            case 3:
                if (I_DICE_PUSH_1 == null) {
                    if (isChallenger) {
                        I_DICE_PUSH_1 = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/home-3.png"));
                    } else {
                        I_DICE_PUSH_1 = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/away-3.png"));
                    }
                }
                return I_DICE_PUSH_1;
            case 4:
                if (I_DICE_PUSH_2 == null) {
                    if (isChallenger) {
                        I_DICE_PUSH_2 = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/home-4.png"));
                    } else {
                        I_DICE_PUSH_2 = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/away-4.png"));
                    }
                }
                return I_DICE_PUSH_2;
            case 5:
                if (I_DICE_POW_DODGE == null) {
                    if (isChallenger) {
                        I_DICE_POW_DODGE = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/home-5.png"));
                    } else {
                        I_DICE_POW_DODGE = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/away-5.png"));
                    }
                }
                return I_DICE_POW_DODGE;
            case 6:
                if (I_DICE_POW == null) {
                    if (isChallenger) {
                        I_DICE_POW = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/home-6.png"));
                    } else {
                        I_DICE_POW = new ImageIcon(daBlock.class.getResource("/resources/images/dice/block/away-6.png"));
                    }
                }
                return I_DICE_POW;
        }
        return null;
    }

    protected void scatterBall(dSquare s) {
        boolean ballStop = false;
        dSquare fromSq = s;
        boolean catched = false;

        try {
            while ((!ballStop) && (!catched)) {
                drScatter rollscatter = new drScatter(_model, fromSq, true, true);
                int r = rollscatter.rollDices();
                _model.AddDiary("Scatter roll: " + r);
                rollscatter.applyEffects(r);
                fromSq = rollscatter.getCurrentSquare();

                rmiPlayer tmp_player = null;
                int i = _myTeam.getPlayerNumber(fromSq.getX(), fromSq.getY());
                {
                    if (i > -1) {
                        tmp_player = (rmiPlayer) _myPlayers.get(i);
                    } else {
                        i = _opponent.getPlayerNumber(fromSq.getX(), fromSq.getY());
                        if (i > -1) {
                            tmp_player = (rmiPlayer) _opponentPlayers.get(i);
                        }
                    }
                }
                if (tmp_player != null) {
                    draGetBall getBall_roll = new draGetBall(tmp_player);
                    int tz = tmp_player.getTackleZoneNumber();
                    getBall_roll.addModifiers(-tz);
                    int result = getBall_roll.rollDices();
                    _model.AddDiary("Get the ball roll: " + result);
                    if (getBall_roll.isSuccess(result)) {
                        tmp_player.setBall(true);
                        catched = true;
                    }

                } else {
                    ballStop = true;
                    _model.setBall(fromSq);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            ;
        }
    }
}
