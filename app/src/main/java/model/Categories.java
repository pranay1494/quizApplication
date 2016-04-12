package model;

/**
 * Created by root on 24/2/16.
 */
public class Categories
{
    private String categoryName;

    private int categoryId;

    public String getCategoryName ()
    {
        return categoryName;
    }

    public void setCategoryName (String categoryName)
    {
        this.categoryName = categoryName;
    }

    public int getCategoryId ()
    {
        return categoryId;
    }

    public void setCategoryId (int categoryId)
    {
        this.categoryId = categoryId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [categoryName = "+categoryName+", categoryId = "+categoryId+"]";
    }
}