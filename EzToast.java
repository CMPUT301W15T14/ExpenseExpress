


public interface EzToast {

    /**
     * Activity:
     *
     * Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
     *
     * Fragment:
     *
     * Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
     */
    public void toast(String message);
}
