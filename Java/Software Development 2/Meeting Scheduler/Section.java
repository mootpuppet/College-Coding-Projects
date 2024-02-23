/*
 * Course: CS1021-021
 * Winter 2022-2023
 * File header contains class Section
 * Name: mottc
 * Created 12/7/2022
 */
package mottc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Course CS1021-021
 * Winter 2022-2023
 * Section purpose: a section object that contains a list of meetings and
 * can be added to a calendar. implements the Schedulable interface
 *
 * @author mottc
 * @version created on 12/7/2022 at 8:51 AM
 */
public class Section implements Schedulable {

    private final List<Schedulable> items = new ArrayList<>();
    private final String name;

    /**
     * Section constructor, assigns name and adds any Scheudlable
     * items to an arrayList called items.
     * @param name - name of section
     * @param items - meeting object(s)
     */
    public Section(String name,Schedulable... items ){

        this.name =name;

        this.items.addAll(Arrays.asList(items));

    }//end section

    /**
     * isBusy checks to see if there is a meeting scheduled at the input day and time
     * @param day - day being checked
     * @param hour - time being checked
     * @return - true or false. true if there is a meeting scheduled at the specified day and time
     */
    @Override
    public boolean isBusy(int day, int hour) {

        boolean busy=false;

        for (Schedulable item: items){

            if(item.isBusy(day,hour)){
                busy=true;

            }//end if

        }//end for

       return busy;

    }//end isBusy

    /**
     * adds a meeting to the section
     * @param item - meeting
     * @return - true if successfully added. false, otherwise
     */
    @Override
    public boolean add(Schedulable item) {

        return items.add(item);

    }//end add

    /**
     * removes a meeting from this section
     * @param item - meeting
     * @return - true if successfully removed. otherwise, false
     */
    @Override
    public boolean remove(Schedulable item) {

        return items.remove(item);

    }//end remove

    /**
     * returns name of section
     * @return - name of section
     */
    @Override
    public String getName() {
        return name;
    }//end getName


}//end Section