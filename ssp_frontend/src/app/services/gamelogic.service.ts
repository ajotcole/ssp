export const playRockPaperScissors = (userChoice: string) => {
  const choices = ['rock', 'paper', 'scissors'];
  const computerChoice = choices[Math.floor(Math.random() * choices.length)];

  userChoice = userChoice.toLowerCase();

  if (!choices.includes(userChoice)) {
    return 'Invalid choice. Please choose rock, paper, or scissors.';
  }

  if (userChoice === computerChoice) {
    return `It's a tie! Both chose ${userChoice}.`;
  }

  if (
    (userChoice === 'rock' && computerChoice === 'scissors') ||
    (userChoice === 'paper' && computerChoice === 'rock') ||
    (userChoice === 'scissors' && computerChoice === 'paper')
  ) {
    return `You win! ${userChoice} beats ${computerChoice}.`;
  } else {
    return `You lose! ${computerChoice} beats ${userChoice}.`;
  }
};
