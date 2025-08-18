package mars.venus;

/**
 * Implemented by classes that represent/contain list(s) of register names
 * that can be visually highlighted by a selection in a source code editor.
 */
public interface RegisterSourceUsageHighlight {
    /**
     * Set the names of the registers that should be highlighted by
     * a selection in the source code editor.
     */
    public void setUsedRegisterNames(String[] names);
}
