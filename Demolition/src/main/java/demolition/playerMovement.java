package demolition;

/**
* Interface which is used to force classes to contain certain methods if they are moveable. Contains methods which will move the player Left, Right, Up and Down as well as change orientation.
*/
interface playerMovement{

    void movementLeft();

    void movementRight();

    void movementUP();

    void movementDOWN();

    void changeOrientation(int orientation);


}