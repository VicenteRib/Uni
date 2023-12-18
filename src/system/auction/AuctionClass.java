/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.auction;

import dataStructures.List;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import system.artWork.Work;

import java.io.Serializable;

/**
 * Auction Class
 */
public class AuctionClass implements Auction, Serializable {

    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 0L;

    /**
     * Constant to check if a given work doesn't exist on an auction
     */
    private static final int NO_WORK = -1;

    /**
     * ID of the auction
     */
    private String ID;
    /**
     * Works that are being auctioned
     */
    private List<Work> auctioning;

    /**
     * AuctionClass constructor
     * @param ID ID of the auction
     */
    public AuctionClass(String ID){
        this.ID = ID;
        auctioning = new DoubleList<>();
    }

    @Override
    public String getID(){
        return ID;
    }

    @Override
    public boolean isEmpty(){
        return auctioning.isEmpty();
    }

    @Override
    public boolean hasWork(Work work){
        return auctioning.find(work)!=NO_WORK;
    }

    @Override
    public Work getWork(String workID){
        Iterator<Work> iterator = auctioning.iterator();
        Work result = null;
        boolean found = false;
        while(iterator.hasNext() && !found) {
            Work work = iterator.next();
            if(work.getID().toLowerCase().equals(workID)){
                result=work;
                found=true;
            }
        }return result;
    }

    @Override
    public void addWorkAuction(Work work, int minPrice){
        auctioning.addLast(work);
        work.setMinimumPrice(minPrice);
    }

    @Override
    public Iterator<Work> listAuctionWorks(){
        return auctioning.iterator();
    }

}
