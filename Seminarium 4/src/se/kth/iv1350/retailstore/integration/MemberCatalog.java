package se.kth.iv1350.retailstore.integration;

/**
 * Represents a catalog containing members of a store.
 */
public class MemberCatalog {
    private String[] memberIDs;

    /**
     * Creates and initializes a catalog of members.
     * Used mainly for simulation purposes.
     */
    public MemberCatalog(){
        this.memberIDs = initializeMembers();
    }

    /**
     * Searches for member in catalog.
     * @param memberID the ID to be checked in the catalog.
     * @return boolean of confirmed membership.
     */
    public boolean searchMember (String memberID){
        for (String member : this.memberIDs){
            if(memberID.equals(member))
                return true;
        }
        return false;
    }

    private String[] initializeMembers(){
        return new String[]{"9007182743","9112145544"};
    }
}
