# NeoHashMap
Configurable multi-layered hashmap  

## Disclaimer
This repo is aimed as a delivery for a technical test in my job application process so the code is as it and will surely not have much updates.

## Description 
NeoHashMap is a hashmap reimplementation based on ``ArrayList`` uses for dynamic allocation.  
It is actually more of a hash tree than a hashmap. Because I did not know hash tree were a thing and was already implemented in Java, though I knew about the concept but could not put a name on it, I decided to keep HashMap as the name as it is what I was asked to do.  
I just decided to do more than what I've been asked to do to prepare myself better in a language I did not practice for so long.

## Features
* It provides hashing of ``String`` keys only. It can surely be extended to every object by using ``object.hashCode()`` but it makes the result harder to manipulate and to present so I am just using ``String`` as the type.
* It is possible to declare new settings through ``NeoHashMapSettings``
Per default, It will hash with the first character of the key.
* By setting ``hashMapLayers`` in ``NeoHashMapSettings`` to ``1``, the NeoHashMap is truly a hashmap.  
By setting it to a bigger number, it is a hash tree.