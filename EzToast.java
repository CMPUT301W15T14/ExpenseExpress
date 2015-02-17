public interface EzToast {

package jz.ironjungle.helper;

public interface EzToast {

/**
 *
 * tldr, it's like the normal way to make Toast but less typing, adds spaces for you, and lets you
 * use a lone number instead of concatenating it to an empty String first
 *
 * Copy paste this:

    @Override

    public void toast(Object ... varargs){
        if (varargs.length == 0){
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
        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        // Use this instead if not in Fragment:
        // Toast.makeText(getActivity(), sb.toString(), Toast.LENGTH_SHORT).show();
    }

 *
 *
 *
 *
 */

     public void toast(Object ... varargs);
}
