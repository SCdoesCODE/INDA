// Stefan Nilsson 2013-03-13

// This program implements an ELIZA-like oracle (en.wikipedia.org/wiki/ELIZA).
package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"strings"
	"time"
)

const (
	star                = "Pythia"
	venue               = "Delphi"
	prompt              = "> "
)

func main() {
	fmt.Printf("Welcome to %s, the oracle at %s.\n", star, venue)
	fmt.Println("Your questions will be answered in due time.")

	oracle := Oracle()
	reader := bufio.NewReader(os.Stdin)
	for {
		fmt.Print(prompt)
		line, _ := reader.ReadString('\n')
		line = strings.TrimSpace(line)
		if line == "" {
			continue
		}
		fmt.Printf("%s heard: %s\n", star, line)
		oracle <- line // The channel doesn't block.
	}
}

// Oracle returns a channel on which you can send your questions to the oracle.
// You may send as many questions as you like on this channel, it never blocks.
// The answers arrive on stdout, but only when the oracle so decides.
// The oracle also prints sporadic prophecies to stdout even without being asked.
func Oracle() chan<- string {
	questions := make(chan string)
	prophecies := make(chan string)

	go generateProphecies(questions, prophecies)
	go printProphecies(prophecies)

	return questions
}

func printProphecies(answers <-chan string) {
	for answer := range answers {
		fmt.Print("\r")
		for _, char := range answer {
			time.Sleep(time.Duration(20+rand.Intn(10)) * time.Millisecond)
			fmt.Print(string(char))
		}
		fmt.Print("\n", prompt)
	}
}

func generateProphecies(questions chan string, prophecies chan string) {
	for {
		// Keep them waiting. Pythia, the original oracle at Delphi,
		// only gave prophecies on the seventh day of each month.
		time.Sleep(time.Duration(10+rand.Intn(5)) * time.Second)
		select {
		case question := <-questions:
			if question == "" {
				prophecies <- "Speak you peasant!I do not have all day."
			} else {
				prophecy(question, prophecies)
			}
		default:
			prophecy("", prophecies)
		}
	}
}



// This is the oracle's secret algorithm.
// It waits for a while and then sends a message on the answer channel.
// TODO: make it better.
func prophecy(question string, answer chan<- string) {

	// Keep them waiting. Pythia, the original oracle at Delphi,
	// only gave prophecies on the seventh day of each month.
	time.Sleep(time.Duration(20+rand.Intn(10)) * time.Second)

	// Find the longest word.
	longestWord := ""
	words := strings.Fields(question) // Fields extracts the words into a slice.
	for _, w := range words {
		if len(w) > len(longestWord) {
			longestWord = w
		}
	}

	// Cook up some pointless nonsense.
	nonsense := []string{
		"The moon is dark.",
		"The sun is bright.",
		"I like nonsense; it wakes up the brain cells.",
		"An apple a day keeps anyone anyway, if you throw it hard enough.",
		"The best things in life are actually very expensive",
		"Enjoy the good times, because something bad will probably happen",
		"Never. Give Up.",


	}

	answer <- longestWord + "... " + nonsense[rand.Intn(len(nonsense))]
}

func init() { // Functions called "init" are executed before the main function.
	// Use new pseudo random numbers every time.
	rand.Seed(time.Now().Unix())
}
