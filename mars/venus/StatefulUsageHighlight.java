package mars.venus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import mars.mips.hardware.Register;

public class StatefulUsageHighlight implements RegisterSourceUsageHighlight {

    private Map<Integer, Boolean> usageHighlightRows;
    private Register[] registers;
    private Redrawer redrawer;

    public interface Redrawer {
        public void redraw();
    }

    public StatefulUsageHighlight(Register[] registers, Redrawer redrawer) {
        this.registers = registers;
        this.redrawer = redrawer;
        usageHighlightRows = new HashMap<Integer, Boolean>();
    }

    public boolean isRowHighlighted(int row) {
        if (usageHighlightRows.containsKey(row)) {
            return usageHighlightRows.get(row);
        }
        return false;
    }

    @Override
    public void setUsedRegisterNames(String[] names) {
        Arrays.sort(names);
        usageHighlightRows = new HashMap<Integer, Boolean>();
        for (int i = 0; i < registers.length; i++) {
            Register reg = registers[i];
            if (Arrays.binarySearch(names, reg.getName()) >= 0) {
                usageHighlightRows.put(reg.getNumber(), true);
            }
        }
        redrawer.redraw();
    }

}
