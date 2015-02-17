public interface EzLog {


    /**
     * copy paste then autoformat:
     *
     * public void log(Object... varargs){if (varargs.length == 0){Log.d("REPLACEME", ".");return;}StringBuilder sb = new StringBuilder();for (int i = 0; i < varargs.length; i++) {   if (varargs[i] instanceof Number || varargs[i] instanceof CharSequence) {sb.append(varargs[i]);StringBuilder sb2 = new StringBuilder();String string = sb2.append(varargs[i]).toString();if (!(string.charAt(string.length() - 1) == ' ')) {sb.append(' '); } } else {sb.append("[*Not Number/CharSequence]");}}Log.d("REPLACEME", sb.toString());}
     *
     */
    public void log(Object... varargs);
}
