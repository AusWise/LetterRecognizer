package model;


import Jama.Matrix;
import java.util.Iterator;
import java.util.List;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */


public abstract class Model
{
    public static final double TWO_PI = 6.283185307179586D;
    
    ExtractFeatures extractor;
    LearningSeries D;
    Character [] Y;
    int f;
    
    Matrix [] mi;
    Matrix [] covariants;
    int [] sizes;
    double py[];
    
    public Model(ExtractFeatures extractor, LearningSeries D, Character [] Y)
    {
        this.D = D;
        this.extractor=extractor;
        this.Y=Y;
        f=2;
    }
    
    public final void learn()
    {
        sizes = getSizes();
        
        mi = new Matrix[Y.length];
        covariants = new Matrix[Y.length];
        py = new double[Y.length];
        
        for(int k=0;k<Y.length;k++)
        {
            mi[k] = getMi(k);
            covariants[k] = getCovariants(k);
            py[k] = ((double)sizes[k])/D.D.size();
        }
            
    }
    
    public final int classification(Matrix Fi)
    {
        double max = apriori(Fi,mi[0],covariants[0])*py[0];
        int result=0;
        double aposteriori;
        for(int k=1;k<Y.length;k++)
        {
            aposteriori = apriori(Fi,mi[k],covariants[k])*py[k];
            if(max< aposteriori)
            {
                result=k;
                max=aposteriori;
            }
        }
        
        return result;
    }
    
    private final Matrix getMi(int k)
    {
        int Nk=0;
        Matrix result = new Matrix(2,1);
        for(Pair<Matrix,Integer> p : D.D)
        {
            if(p.b==k)
            {
                result =result.plus(p.a);
                Nk+=1;
            }
        }
        
        result = result.times(1.0D/Nk);
        
        return result;
    }
    
    private final Matrix getCovariants(int k)
    {
        int Nk=0;
        Matrix result = new Matrix(2,2);
        Matrix left,right;
        for(Pair<Matrix,Integer> p : D.D)
        {
            if(p.b==k)
            {
                left=p.a.minus(mi[k]);
                right=p.a.minus(mi[k]).transpose();
                result =result.plus(left.times(right));
                Nk+=1;
            }
        }
        
        result = result.times(1.0D/Nk);
        
        return result;
    }
    
    private final int[] getSizes()
    {
        int [] result = new int [Y.length];
        for(int k=0;k<Y.length;k++)
            result[k] = 0;
        
        for(Pair<Matrix,Integer> p : D.D)
            result[p.b]+=1;
        
        return result;
    }
    
    private final double apriori(Matrix Fi, Matrix mi, Matrix covariants)
    {
        Matrix invCov = covariants.inverse();
        Matrix right = Fi.minus(mi);
        Matrix left = right.transpose();
        Matrix result = left.times(invCov);
        result = result.times(right);
        double det = Math.abs(covariants.det());
        double res = result.get(0, 0);
        
        return (1.0D/(TWO_PI*det))*Math.exp(-0.5D * res);
    }
    
    public Character [] getY()
    {
        return Y;
    }
    
    public LearningSeries getD()
    {
        return D;
    }
    
    public ExtractFeatures getExtractor()
    {
        return extractor;
    }
    
}

