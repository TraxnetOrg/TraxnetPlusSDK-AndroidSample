package ee.traxnet.plussample.android.model;

import java.io.Serializable;

import ee.traxnet.plussample.android.enums.ListItemType;

public class ItemList implements Serializable {
    public ListItemType listItemType;
    public String id;
    public String title;
}
