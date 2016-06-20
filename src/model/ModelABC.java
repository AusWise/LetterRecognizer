package model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class ModelABC extends Model
{
    
    private final static Character [] Y = {'A','B','C'};
    public ModelABC()
    {
        super(new ExtractFeaturesABC(), new LearningSeries(), Y);
    }
    
    public String toString()
    {
        return "ABC";
    }
    
    
}
