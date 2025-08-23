package mars.venus;

import mars.venus.editors.jeditsyntax.JEditTextArea;

/**
 * A behaviorless (by default) class containing information on an event
 * that is triggered whenever the selection of a text area changes.
 */
public class SelectionEvent {
    private int selectionStart;
    private int selectionEnd;
    private JEditTextArea textArea;

    public int getSelectionStart() {
        return selectionStart;
    }

    public int getSelectionEnd() {
        return selectionEnd;
    }

    public JEditTextArea getTextArea() {
        return textArea;
    }

    public SelectionEvent(int selectionStart, int selectionEnd, JEditTextArea textArea) {
        this.selectionStart = selectionStart;
        this.selectionEnd = selectionEnd;
        this.textArea = textArea;
    }

}
