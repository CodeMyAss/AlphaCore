/*
 *  Created by Filip P. on 2/9/15 7:07 PM.
 */

package me.pauzen.alphacore.utils.misc;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
public @interface Todo {

    String value();

}
