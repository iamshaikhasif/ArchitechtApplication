package shock.com.architechtapplication.base

interface BaseView {
    /**
     * Show progress.
     */
    fun showProgress()

    /**
     * Hide progress.
     */
    fun hideProgress()

    /**
     * On unknown error.
     */
    fun onUnknownError()

    /**
     * Show short message.
     *
     * @param msg the msg
     */
    fun showShortMessage(msg: String)

    /**
     * Show long message.
     *
     * @param msg the msg
     */
    fun showLongMessage(msg: String)

    /**
     * On back button pressed boolean.
     *
     * @return the boolean
     */
    fun onBackButtonPressed(): Boolean
}
