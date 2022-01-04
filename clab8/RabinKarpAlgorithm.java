public class RabinKarpAlgorithm {

    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        if (pattern.length() > input.length()) {
            return -1;
        }

        RollingString p = new RollingString(pattern, pattern.length());
        int hpattern = p.hashCode();

        RollingString temp = new RollingString(input.substring(0, p.length()), p.length());

        int match = 0;
        int track = p.length() - 1;
        while (track < input.length()) {
            int htrial = temp.hashCode();
            if (hpattern == htrial) {
                if (p.equals(temp)) {
                    return match;
                }
            }
            match += 1;
            track += 1;
            temp.addChar(input.charAt(track));
        }
        return -1;
    }
}
