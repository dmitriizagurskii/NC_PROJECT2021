package com.netcracker.edu.interview.services.utils;


import com.netcracker.edu.interview.domain.CodeChangeInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class CodeUtility {


    public static void applyChange(StringBuilder text, CodeChangeInfo changeInfo) {
        WrapInt start = new WrapInt();
        WrapInt end = new WrapInt();

        transformCoordinates(text, changeInfo.getFrom(), changeInfo.getTo(), start, end);


        if (end.value >= text.length()) text.append(String.join("", Collections.nCopies(end.value - text.length() + 256, " ")));


        if (start.value == end.value && changeInfo.getText().isEmpty()) {
            text.replace(start.value, end.value, "\n");
        } else {
            text.replace(start.value, end.value, changeInfo.getText());
        }
        log.info("Updated text: " + text);
    }

    // transforming coordinates: {from: {line: int, ch: int}, to: {line: int, ch: int}} -> {start, end}
    private static void transformCoordinates(StringBuilder text, CodeChangeInfo.From from, CodeChangeInfo.To to,
                                     WrapInt start, WrapInt end) {
//        log.info("from: " + from.getLine() + ", " + from.getCh() + "; to: " + to.getLine() + ", " + to.getCh());

        if (to.getLine() == 0) {
            start.value = from.getCh();
            end.value = to.getCh();
        } else {
            List<Integer> newLineCoordinates = new LinkedList<>();
            int newLineIndex = text.indexOf("\n");
            newLineCoordinates.add(newLineIndex);

            while ((newLineIndex = text.indexOf("\n", newLineIndex + 1)) != -1) {
                newLineCoordinates.add(newLineIndex);
            }

            if (from.getLine() == 0) {
                start.value = to.getCh();
            } else {
                start.value = newLineCoordinates.get(from.getLine() - 1) + from.getCh() + 1;
            }

            end.value = newLineCoordinates.get(to.getLine()-1) + to.getCh() + 1;

        }

//    log.info("start: " + start.value + ", end:" + end.value);


    }

}
