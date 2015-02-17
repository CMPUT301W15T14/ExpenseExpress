public interface EzLog {

    public void log(String message);

    public void log();

    /**
     * Copy-paste this:
     *
     * Integer.valueOf(i).toString();
     *
     * @param i
     * @return
     */
    public String str(int i);
}
