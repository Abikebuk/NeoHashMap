# NeoHashMap
Configurable multi-layered hashmap  

## Disclaimer
This repo is aimed as a delivery for a technical test in my job application process so the code is as it and will surely not have much updates.  
I tried to not peak at any Java implementation of the actual ``HashMap`` but, in the end, took a look at how they implemented the sizing of the ``HashMap`` which was just 2^hashSize (for me)  
The naming of settings, variables and methods might be wrong because of this fact plus the fact I did not practice Java in a few year.  
Finally I might have completely missed the point in the implementation of the ``HashMap``. 

## Description 
NeoHashMap is a hashmap reimplementation that is my own interpretation on how it should be implemented considering the its theoretical definition.
It is actually more of a hash tree than a hashmap. Because I did not know hash tree were has this name and was already implemented in Java, though I knew about the concept but could not put a name on it, I decided to keep HashMap as the name as it is what I was asked to do.  
I just decided to do more than what I've been asked to do to prepare myself better in a language I did not practice for so long.

## Features
* It should work on any ``Object`` key and every ``Object`` value.
* It is possible to declare new settings through ``NeoHashMapSettings``  
Per default, It will hash with the first binary value of the key with a layer of 1.
* By setting ``hashMapLayers`` in ``NeoHashMapSettings`` to ``1``, the NeoHashMap is truly a hashmap.  
By setting it to a bigger number, it is a hash tree.

## Settings 
There are 3 different settings that can be used with their default settings declared below: 
````javascript
/**
 * Defines the possibility of overflow 
 * The overflow is representated by a single chained list which can also be representated as a FIFO
 */
boolean overflow = true;

/**
 * The hashCodeSize represents the number of binary number taken as key for each nodes
 * As an example with 10110 and a hash code of 1 : the key would be   0
 *               with 10110 and a hash code of 3 : the key would be 110
 * It just takes the last numbers of the binary key
 */
int hashCodeSize = 1;

/**
 * The hashMapLayers represents the number of layer it represent
 * As an example with a hashCodeSize of 1, it is possible to have 2 values : 0 and 1
 * Taking this in account, with a hashMapLayers = 1, we would have : 
 *      root :  
 *       |-  0 
 *       |-  1
 * With a hashMapLayer = 2, we would have : 
 *      root : 
 *       |-  0
 *       |   |- 0
 *       |   |- 1
 *       |-  1
 *       |   |- 0
 *       |   |- 1
 */
int hashMapLayers = 2;
````

## How to use : 

To use the NeoHashMap :
````java
int hashMapLayer = 2;
int hashCodeSize = 3;
boolean canOverflow = true;

NeoHashMapSettings settings = new NeoHashMapSettings(hashMapLayers, hashCodeSize, canOverflow);
NeoHashMap hahsMap = new NeoHashMap<String, String>(settings); // new NeoHashMap() without argument would work but with default settings
                                   //  ^  and  ^  both type can be any object
// Add one element        
hashMap.add("key", "value");
// Remove the first element on the key
hashMap.removeFirst("key");
// Remove all the element on the key
hashMap.removeAll("key");
// Print the hashmap
hashmap.print();
````

## Sample
There is a sample in the Main class which can be executed.