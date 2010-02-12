/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbos.General.Views.Match;

import bbos.General.Model.mMatch;
import bbos.General.Model.mTeam;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author root
 */
public class jtrMatch extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{
    mTeam _team;
    
    public jtrMatch(mTeam team)
    {
        _team=team;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column)
    {
        if (value.getClass().equals(mMatch.class))
        {
            mMatch match = (mMatch) value;
            if (match.getState() == mMatch.STATE_DONE)
            {
                jpnCellMatchDone cell = new jpnCellMatchDone(row, match,_team);
                cell.repaint();
                return cell;
            }
            else if (match.getState() == mMatch.STATE_IN_COURSE)
            {
                jpnCellMatchInCourse cell = new jpnCellMatchInCourse(row, match,_team);
                cell.repaint();
                return cell;
            }
            else
            {
                jpnCellMatchNext cell = new jpnCellMatchNext(row, match,_team,table);
                cell.repaint();
                return cell;
            }
        }
        else
        {
            return new JLabel("Unknown");
        }
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col)
    {
        if (value.getClass().equals(mMatch.class))
        {
            mMatch match = (mMatch) value;
            if (match.getState() == mMatch.STATE_DONE)
            {
                return new jpnCellMatchDone(row, match,_team);
            }
            else if (match.getState() == mMatch.STATE_IN_COURSE)
            {
                return new jpnCellMatchInCourse(row, match,_team);
            }
            else
            {
                return new jpnCellMatchNext(row, match,_team,table);
            }
        }
        else
        {
            return new JLabel("Unknown");
        }
    }

    //inherited
    public Object getCellEditorValue()
    {
        return null;
    }
}
