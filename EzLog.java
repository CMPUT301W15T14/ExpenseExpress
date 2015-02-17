public interface EzLog {

    /**
     *
     * tldr:
     * you can use comma instead of + (optional)
     * it's easier to type
     * if you use comma then it will add spaces for you automatically
     * you can use a lone Number without having to concatenate it to a String and it will log it appropriately
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
