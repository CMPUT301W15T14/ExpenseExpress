public interface EzLog {

    /**
     *
     * Example:
     *
     * Log.d("YourTag", "Number inside claims list: " + Integer.valueOf(claimsList.size()).toString());
     *
     * becomes:
     *
     * log("Number inside claims list:", claimsList.size());
     * 
     * You can do log(); and it will give you a '.' and line break, for readability, i.e.:
     *
     * log(); replaces
     * Log.d("YourTag", ".");
     * (for some reason the log cat in my Android Studio messes up if you give it just a whitespace
     * 
     * Notes:
     * - automatically adds spaces between arguments and detects non-Number/String
     * - separate different things by comma, not + (can't concatenate a Number to a String)
     *   (you can still + two Strings but it won't add a space for you if you do that)
     * 
     *
     * copy-paste:

     @Override

     public void log(Object... varargs) {
        if (varargs.length == 0){
            Log.d("REPLACEME", ".");
            return;
        }
        StringBuilder sb = new StringBuilder();
            for (int i = 0; i < varargs.length; i++) {
                if (varargs[i] instanceof Number || varargs[i] instanceof CharSequence) {
                    sb.append(varargs[i]);
                    StringBuilder sb2 = new StringBuilder();
                    String string = sb2.append(varargs[i]).toString();
                    if (!(string.charAt(string.length() - 1) == ' ')) {
                        sb.append(' ');
                    }
                } else {
                    sb.append("[*Not Number/CharSequence]");
                }
            }
        Log.d("REPLACEME", sb.toString());
     }

     *
     */

    public void log(Object... varargs);
}
