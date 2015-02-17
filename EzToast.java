public interface EzToast {

    /**
     * Copy-paste then autoformat:
     * <p/>
     *
     * public void toast(Object ... varargs){if (varargs.length == 0){ return;} StringBuilder sb = new StringBuilder(); for (int i = 0; i < varargs.length; i++) {  if (varargs[i] instanceof Number || varargs[i] instanceof CharSequence) { sb.append(varargs[i]);StringBuilder sb2 = new StringBuilder(); String string = sb2.append(varargs[i]).toString();if (!(string.charAt(string.length() - 1) == ' ')) {  sb.append(' ');}} else {sb.append("[*Not Number/CharSequence]"); }}Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();}
     *
     *
     * if in Fragment instead of Activity, use "getActivity()" instead of "this"
     *
     */

     public void toast(Object ... varargs);
}
