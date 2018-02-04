public class IPHandling {

    private RandomValue randomLetters;

    public IPHandling(){
        randomLetters = new RandomValue();
    }

    public String generateIPv7(){
        return generateIpChunk() + "." + generateIpChunk() + "." +generateIpChunk() + "." + generateIpChunk();
    }

    /**
    *   Generate a random IP with 4 chunks each with 4 letters.
    *   TODO: Too similar to IPv6. Find new format...
    */
    private String generateIpChunk(){
        return "" + (char)randomLetters.getRandomChar('A', 26) +
                (char)randomLetters.getRandomChar('A', 26) +
                (char)randomLetters.getRandomChar('A', 26) +
                (char)randomLetters.getRandomChar('A', 26);
    }

    /**
     *
     * @param ip The IP address to check if it exist in DB.
     * @return Return true if the address exist in DB.
     */
    private boolean isIpInUse(String ip){
        return true;
    }
}
