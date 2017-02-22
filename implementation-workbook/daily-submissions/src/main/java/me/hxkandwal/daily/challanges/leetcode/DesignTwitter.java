package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 355. Design Twitter
 * 
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent 
 * tweets in the user's news feed. Your design should support the following methods:
 * 
 * 	postTweet(userId, tweetId)	: 	Compose a new tweet.
 * 	getNewsFeed(userId)			: 	Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users 
 * 						 			who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 * 	follow(followerId, followeeId): Follower follows a followee.
 * 	unfollow(followerId, followeeId): Follower unfollows a followee.
 * 
 * Example:
 * 
 * 	Twitter twitter = new Twitter();
 *  // User 1 posts a new tweet (id = 5).
 *  twitter.postTweet(1, 5);
 *  
 *  // User 1's news feed should return a list with 1 tweet id -> [5].
 *  twitter.getNewsFeed(1);
 *  
 *  // User 1 follows user 2.
 *  twitter.follow(1, 2);
 *  
 *  // User 2 posts a new tweet (id = 6).
 *  twitter.postTweet(2, 6);
 *  
 *  // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 *  // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 *  twitter.getNewsFeed(1);
 *  
 *  // User 1 unfollows user 2.
 *  twitter.unfollow(1, 2);
 *  
 *  // User 1's news feed should return a list with 1 tweet id -> [5],
 *  // since user 1 is no longer following user 2.
 *  twitter.getNewsFeed(1);
 * 
 * @author Hxkandwal
 */
public class DesignTwitter extends AbstractCustomTestRunner {

	private static DesignTwitter _instance = new DesignTwitter();
	
	public int timestamp = 0;
	// instead of map, we can create objects and use one map for all object referencing.
    Map <Integer, LinkedList<Tweet>> userTweets = new HashMap<>();
    Map <Integer, Set<Integer>> followers = new HashMap<>();
    
    public class Tweet implements Comparable<Tweet> {
        int timestamp;
        int tid;
        
        public Tweet (int timestamp, int tid) {
            this.timestamp = timestamp; this.tid = tid;    
        }
        
        public int compareTo(Tweet that) {
            if (this.timestamp == that.timestamp) return this.tid - that.tid; 
        	return this.timestamp - that.timestamp;
        }
    }
    
    /** Initialize your data structure here. */
    public DesignTwitter() {
		
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        userTweets.putIfAbsent(userId, new LinkedList<>());
        userTweets.get(userId).addFirst(new Tweet(timestamp ++, tweetId));
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> response = new ArrayList<>();
        
        List<Iterator<Tweet>> iterators = new ArrayList<>();
        if (userTweets.containsKey(userId))
            iterators.add (userTweets.get(userId).iterator());
        
        if (followers.containsKey(userId))
            for (Integer followerId : followers.get (userId)) 
                if (userTweets.containsKey(followerId))
                    iterators.add (userTweets.get(followerId).iterator());
        
        PriorityQueue<Tweet> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        int size = 10;
        while (size -- > 0) {
            for (Iterator<Tweet> iterator : iterators) 
                if (iterator.hasNext()) maxheap.offer (iterator.next());
            if (!maxheap.isEmpty())
                response.add (maxheap.poll().tid);
            else break;
        }

        return response;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        followers.putIfAbsent(followerId, new HashSet<Integer>());
        followers.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followers.containsKey(followerId))
            followers.get(followerId).remove(followeeId);
    }
   
   	// driver method
   	public static void main(String[] args) {
		_instance.postTweet(1, 1);
		_instance.postTweet(1, 2);
		_instance.postTweet(2, 4);
		_instance.postTweet(2, 5);
		_instance.getNewsFeed(1).stream().forEach((i) -> { System.out.print(i  + " "); });
		System.out.println();
		_instance.follow(1, 2);
		_instance.getNewsFeed(1).stream().forEach((i) -> { System.out.print(i  + " "); });
		System.out.println();
   	}
    
}
