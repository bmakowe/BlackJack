package de.htwg.blackjack.view.tui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.blackjack.controller.logwrapper.BlackJackController;
import de.htwg.blackjack.model.Rules;
import de.htwg.blackjack.model.Statistic;

public class TUITest {

	private TextUI tui;
	
	@Before
	public void setUp() {
		tui = new TextUI(new BlackJackController(new Rules(), new Statistic()));
	}
	
	@Test
	public void testHandleInput() {
		assertTrue(tui.handleInput("start10"));
		assertTrue(tui.handleInput("stand"));
		assertTrue(tui.handleInput("hit"));
		assertTrue(tui.handleInput("double"));
		assertTrue(tui.handleInput("split"));
		assertTrue(tui.handleInput("stat"));
		assertTrue(tui.handleInput("bet10"));
		assertTrue(tui.handleInput("no option"));
		assertFalse(tui.handleInput("exit"));
	}
	
}
