package model;

import Jama.Matrix;
import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author auswise
 */
public class ExtractFeaturesABC implements ExtractFeatures
{
    BufferedImage bimg;
    
    int imin;
        int imax;
        int jmin;
        int jmax; 
    
    public Matrix extract(BufferedImage bimg)
    {
        this.bimg=bimg;
        
       obliczMaxIJ();
        
        Matrix fi = new Matrix(2,1);
        fi.set(0, 0, cechaI(0,0.3));
        fi.set(1, 0, cechaJ(0.4,0.6));
        
        return fi;
        
    }
    
   protected void obliczMaxIJ(){
		for(int i=0; i<bimg.getWidth();i++){
			for (int j=0; j<bimg.getHeight();j++){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					imin=i;
					i=bimg.getWidth();
					j=bimg.getHeight();
				}
			}
		}
		for(int i=bimg.getWidth()-1; i>imin;i--){
			for (int j=bimg.getHeight()-1; j>0;j--){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					imax=i;
					i=0;
					j=0;
				}
			}
		}
		
		for(int j=0;j<bimg.getHeight();j++){
			for (int i=imin; i<=imax;i++){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					jmin=j;
					i=bimg.getWidth();
					j=bimg.getHeight();
				}
			}
		}
		for(int j=bimg.getHeight()-1; j>=jmin;j--){
			for (int i=imax;i>=imin;i--){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					jmax=j;
					i=0;
					j=0;
				}
			}
		}
	}
	
	public double cechaI(double alfa, double beta){ 
		int ilow=(int) (((1-alfa)*imin)+(alfa*imax));
		int ihigh=(int) (((1-beta)*imin)+(beta*imax));
		
		int jmaxH=0;
		int jminH=0;
		
		for(int j=jmin;j<=jmax;j++){ 
			for(int i=ilow;i<=ihigh;i++){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					
					jminH=j;
					j=jmax;
					i=ihigh;
				}
			}
		}
		for(int j=jmax;j>=jmin;j--){ 
			for(int i=ilow;i<=ihigh;i++){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					
					jmaxH=j;
					j=jmin;
					i=ihigh;
				}
			}
		}
		
		double cecha=(((double)(jmaxH-jminH))/((double)(jmax-jmin)));
		
		return cecha;
	}
	
	public double cechaJ(double alfa, double beta){ //zwraca poprawne wyniki, moze lepiej zeby mialo return roznice
		int jlow=(int) (((1-alfa)*jmin)+(alfa*jmax));
		int jhigh=(int) (((1-beta)*jmin)+(beta*jmax));
		
		int iminH=0;
		int imaxH=0;
		
		for(int i=imin;i<=imax;i++){ 
			for(int j=jlow;j<=jhigh;j++){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					
					iminH=i;
					i=imax;
					j=jhigh;
				}
			}
		}
		for(int i=imax;i>=imin;i--){ 
			for(int j=jlow;j<=jhigh;j++){
				if(bimg.getRGB(i, j)==Color.black.getRGB()){
					
					imaxH=i;
					i=imin;
					j=jhigh;
				}
			}
		}
		
		double cecha=(((double)(imaxH-iminH))/((double)(imax-imin)));
		
		return cecha;
	}
}
