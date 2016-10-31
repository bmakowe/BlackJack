package de.htwg.blackjack.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.blackjack.model.Statistic;

public class StatisticTest {
	Statistic statistic;
	
	@Before
	public void setUp() {
		statistic = new Statistic();
	}
	
	@Test
	public void testReset() {
		statistic.won();
		statistic.lost();
		statistic.setStartCash(10);
		statistic.setEndCash(10);
		statistic.lostCash(5);
		statistic.wonCash(5);
		statistic.reset();
		assertEquals(0, statistic.getWins());
		assertEquals(0, statistic.getLooses());
		assertEquals(0, statistic.getStartCash());
		assertEquals(0, statistic.getEndCash());
		assertEquals(0, statistic.getLostCash());
		assertEquals(0, statistic.getWonCash());
	}
	
	@Test
	public void testWon() {
		statistic.won();
		assertEquals(1, statistic.getWins());
	}
	
	@Test
	public void testLost() {
		statistic.lost();
		assertEquals(1, statistic.getLooses());
	}
	
	@Test
	public void testWonCash() {
		statistic.wonCash(1);
		assertEquals(1, statistic.getWonCash());
	}
	
	@Test
	public void testLostCash() {
		statistic.lostCash(1);
		assertEquals(1, statistic.getLostCash());
	}
	
	@Test
	public void testSetStartCash() {
		statistic.setStartCash(1);
		assertEquals(1, statistic.getStartCash());
	}
	
	@Test
	public void testSetEndCash() {
		statistic.setEndCash(1);
		assertEquals(1, statistic.getEndCash());
	}
	
	@Test
	public void testGetStatistic() {
		assertNotNull(statistic.getStatistic("std"));
		assertEquals("Statistic format doesn't exist", statistic.getStatistic("not a format"));
	}
	
	
}
