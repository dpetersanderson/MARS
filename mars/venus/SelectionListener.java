package mars.venus;

import java.util.EventListener;

/**
 * EventListener for the SelectionEvent type.
 */
public interface SelectionListener extends EventListener {
    public void selectionChanged(SelectionEvent evt);
}
