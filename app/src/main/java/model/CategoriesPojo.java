package model;

import java.util.List;

/**
 * Created by root on 24/2/16.
 */
public class CategoriesPojo
{
    private List<Categories> categories;

    private String header;

    public List<Categories> getCategories ()
    {
        return categories;
    }

    public void setCategories (List<Categories> categories)
    {
        this.categories = categories;
    }

    public String getHeader ()
{
    return header;
}

    public void setHeader (String header)
    {
        this.header = header;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [categories = "+categories+", header = "+header+"]";
    }
}