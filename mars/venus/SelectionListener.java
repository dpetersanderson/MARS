package mars.venus;

import java.util.EventListener;

public interface SelectionListener extends EventListener {
    public void selectionChanged(SelectionEvent evt);
}
