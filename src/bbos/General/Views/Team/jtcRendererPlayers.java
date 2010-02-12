/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Team;

import bbos.General.Model.mPlayer;
import bbos.General.Model.mTeam;
import bbos.General.Model.mTeamRoster;
import bbos.mBBoS;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 *
 * @author frederic
 */
public class jtcRendererPlayers extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{

    private JLabel label;
    private JTextField text;
    private JComboBox combo;
    private JCheckBox check;
    private mTeam _team;
    JPanel _panel;
    int _view;

    public jtcRendererPlayers(mTeam team, JPanel panel, int view)
    {
        super();
        _team = team;
        _panel = panel;
        _view = view;
    }

    public Component getTableCellRendererComponent(
            JTable table, Object objet,
            boolean isSelected, boolean hasFocus,
            int row, int col)
    {
        mPlayer player;
        if (_view == jtmTeamPlayers.EX_VIEW)
        {
            Vector players = _team.getExPlayers();
            if ((row >= 0) && (row < players.size()))
            {
                player = (mPlayer) players.get(row);
            }
            else
            {
                player = null;
            }
        }
        else
        {
            player = (mPlayer) _team.getPlayers().get(row + 1);
        }


        if (objet != null)
        {
            /* Name */
            if ((col == 1)&&(row>-1))
            {
                if (_view == jtmTeamPlayers.NEW_VIEW)
                {
                    text = new JTextField();
                    if (String.class.equals(objet.getClass()))
                    {
                        text.setText((String) objet);
                    }
                    text.setHorizontalAlignment(JLabel.CENTER);
                    return text;
                }
                if ((_view == jtmTeamPlayers.FULL_VIEW) || (_view == jtmTeamPlayers.EX_VIEW))
                {
                    label = new JLabel();
                    if (String.class.equals(objet.getClass()))
                    {
                        label.setText((String) objet);
                        label.setToolTipText((String) objet);
                    }
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }
            } /* Position */ else if ((col == 2)&&(row>-1))
            {
                if (_view == jtmTeamPlayers.NEW_VIEW)
                {
                    Vector available_positions = _team.getAvailablePositions();
                    available_positions.add(0, "");
                    if (String.class.equals(objet.getClass()))
                    {
                        available_positions.add((String) objet);
                        combo = new JComboBox(available_positions);
                        combo.setEnabled(true);
                        combo.setSelectedItem(objet);
                    }
                    else
                    {
                        combo = new JComboBox(available_positions);
                        combo.setEnabled(true);
                    }
                    return combo;
                }
                if ((_view == jtmTeamPlayers.FULL_VIEW) || (_view == jtmTeamPlayers.EX_VIEW))
                {
                    label = new JLabel();
                    if (String.class.equals(objet.getClass()))
                    {
                        label.setText((String) objet);
                        label.setToolTipText((String) objet);
                    }
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }

            }
            else if (col == 10)
            {
                if (Integer.class.equals(objet.getClass()))
                {
                    label = new JLabel();
                    label.setText(((Integer) objet).toString());
                    label.setToolTipText(((Integer) objet).toString());
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }
                if (String.class.equals(objet.getClass()))
                {
                    label = new JLabel();
                    label.setText((String) objet);
                    label.setToolTipText((String) objet);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    return label;
                }

                check = new JCheckBox();
                if (Boolean.class.equals(objet.getClass()))
                {
                    check.setSelected((Boolean) objet);
                }

                return check;
            }
            else
            {
                label = new JLabel();
                label.setOpaque(true);
                if (Integer.class.equals(objet.getClass()))
                {
                    if (col == 3)
                    {
                        if (player != null)
                        {
                            int res = player.getMovement() - player.getPlayerType().getMovement();
                            if (res < 0)
                            {
                                label.setForeground(Color.red);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                            if (res > 0)
                            {
                                label.setForeground(Color.blue);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                        }
                    }

                    if (col == 4)
                    {
                        if (player != null)
                        {
                            int res = player.getStrength() - player.getPlayerType().getStrength();
                            if (res < 0)
                            {
                                label.setForeground(Color.red);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                            if (res > 0)
                            {
                                label.setForeground(Color.blue);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                        }
                    }
                    if (col == 5)
                    {
                        if (player != null)
                        {
                            int res = player.getAgility() - player.getPlayerType().getAgility();
                            if (res < 0)
                            {
                                label.setForeground(Color.red);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                            if (res > 0)
                            {
                                label.setForeground(Color.blue);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                        }
                    }

                    if ((col == 17) && (row >= 0))
                    {
                        label.setFont(label.getFont().deriveFont(Font.ITALIC));
                    }

                    if (col == 6)
                    {
                        if (player != null)
                        {
                            int res = player.getArmor() - player.getPlayerType().getArmor();
                            if (res < 0)
                            {
                                label.setForeground(Color.red);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                            if (res > 0)
                            {
                                label.setForeground(Color.blue);
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                        }
                    }

                    if (col == 9)
                    {
                        if (player != null)
                        {
                            if (player.MissNewMatch())
                            {
                                label.setForeground(Color.gray);
                            }
                            int res = player.getCost() - player.getPlayerType().getCost();
                            if (res > 0)
                            {
                                label.setFont(label.getFont().deriveFont(Font.BOLD));
                            }
                        }
                    }
                    if (col == 11)
                    {
                        label.setForeground(Color.red);
                    }
                    label.setText(((Integer) objet).toString());
                    label.setToolTipText(((Integer) objet).toString());
                }
                if (String.class.equals(objet.getClass()))
                {
                    if ((col == 8) && (row >= 0))
                    {
                        if ((_view == jtmTeamPlayers.FULL_VIEW) || (_view == jtmTeamPlayers.EX_VIEW))
                        {
                            label.setFont(label.getFont().deriveFont(Font.BOLD));
                        }
                    }

                    if ((col == 18) && (row >= 0))
                    {
                        if (player != null)
                        {
                            label.setFont(label.getFont().deriveFont(Font.BOLD));
                            if (player.getStatus() == mPlayer.STATUS_ACTIVE)
                            {
                                label.setBackground(Color.GREEN);
                            }
                            if (player.getStatus() == mPlayer.STATUS_RETIRED)
                            {
                                label.setBackground(Color.WHITE);
                            }
                            if (player.getStatus() == mPlayer.STATUS_DEAD)
                            {
                                label.setBackground(Color.RED);
                                label.setForeground(Color.WHITE);
                            }
                        }
                    }
                    label.setText((String) objet);
                    label.setToolTipText((String) objet);
                }
                label.setHorizontalAlignment(JLabel.CENTER);
                return label;
            }
        }
        return new JLabel();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        if (col == 2)
        {
            Vector available_positions = _team.getAvailablePositions();
            available_positions.add(0, "");
            if (String.class.equals(value.getClass()))
            {
                if (!available_positions.contains(value))
                {
                    available_positions.add(value);
                }
            }
            combo = new JComboBox(available_positions);
            combo.setEnabled(true);
            if (String.class.equals(value.getClass()))
            {
                combo.setSelectedItem(value);
            }

            combo.addActionListener(new laPlayers(combo, _team, "Player", _panel, row + 1));
            _panel.repaint();
            return combo;
        }

        if (col == 1)
        {
            if (String.class.equals(value.getClass()))
            {
                text = new JTextField((String)value);
            }
            else
            {
                text=new JTextField("");
            }
            text.addActionListener(new laPlayers(text, _team, "Name", _panel, row));
            text.addFocusListener(new laPlayers(text, _team, "Name", _panel, row));

            _panel.repaint();
            return text;
        }
        _panel.repaint();

        return null;
    }

    //inherited
    public Object getCellEditorValue()
    {
        return null;
    }
}

