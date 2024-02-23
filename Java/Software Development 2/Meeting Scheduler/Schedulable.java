/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains interface Schedulable
 * Name: mottc
 * Created 12/7/2022
 */

package mottc;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Schedulable purpose: an interface for schedulable items
 *
 * @author mottc
 * @version created on 12/7/2022 at 8:59 AM
 */
public interface Schedulable{
    int MONDAY=0;
    int TUESDAY=1;
    int WEDNESDAY=2;
    int THURSDAY=3;
    int FRIDAY=4;

    boolean isBusy(int day, int hour);
    boolean add(Schedulable item);
    boolean remove(Schedulable item);
    String getName();

}



