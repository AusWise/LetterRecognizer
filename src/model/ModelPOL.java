/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author auswise
 */
public class ModelPOL extends Model 
{
    private static Character [] Y = {'P','O','L'};
    public ModelPOL()
    {
        super(new ExtractFeaturesPOL(), new LearningSeries(), Y);
    }
    
    public String toString()
    {
        return "POL";
    }
}
