CREATE DATABASE IF NOT EXISTS bookmatch
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE bookmatch;

CREATE TABLE IF NOT EXISTS livros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    genero VARCHAR(80) NOT NULL,
    avaliacao DECIMAL(3,1) NOT NULL,
    descricao TEXT NOT NULL
);

TRUNCATE TABLE livros;

INSERT INTO livros (titulo, autor, genero, avaliacao, descricao) VALUES
('O Hobbit', 'J.R.R. Tolkien', 'Fantasia', 9.5, 'Uma aventura clássica em uma terra fantástica, com jornadas, mistérios e criaturas inesquecíveis.'),
('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 'Fantasia', 9.2, 'Um jovem descobre que é bruxo e entra em uma escola cheia de magia, amizade e perigos.'),
('As Crônicas de Nárnia', 'C.S. Lewis', 'Fantasia', 8.9, 'Crianças encontram um mundo mágico onde precisam enfrentar grandes desafios e escolhas.'),
('Dom Casmurro', 'Machado de Assis', 'Romance', 9.0, 'Um romance brasileiro marcado por memória, ciúme, ambiguidade e análise psicológica.'),
('Orgulho e Preconceito', 'Jane Austen', 'Romance', 9.1, 'Uma história clássica sobre amor, família, orgulho e relações sociais.'),
('A Culpa é das Estrelas', 'John Green', 'Romance', 8.6, 'Um romance emocionante sobre amor, juventude e fragilidade da vida.'),
('Frankenstein', 'Mary Shelley', 'Terror', 8.8, 'Uma obra clássica sobre criação, ciência, solidão e responsabilidade.'),
('Drácula', 'Bram Stoker', 'Terror', 8.7, 'Um clássico sombrio sobre vampiros, mistério e medo.'),
('It: A Coisa', 'Stephen King', 'Terror', 8.9, 'Um grupo de amigos enfrenta uma presença assustadora que retorna a cada geração.'),
('Duna', 'Frank Herbert', 'Ficção Científica', 9.4, 'Uma saga política, ecológica e científica em um planeta desértico.'),
('Fundação', 'Isaac Asimov', 'Ficção Científica', 9.0, 'Uma obra sobre impérios galácticos, ciência e previsões matemáticas do futuro.'),
('Jogador Nº 1', 'Ernest Cline', 'Ficção Científica', 8.5, 'Uma aventura futurista em realidade virtual repleta de referências à cultura pop.'),
('Assassinato no Expresso do Oriente', 'Agatha Christie', 'Suspense', 8.9, 'Um mistério investigativo com Hercule Poirot dentro de um trem luxuoso.'),
('O Código Da Vinci', 'Dan Brown', 'Suspense', 8.4, 'Uma trama de enigmas, arte, religião e conspirações.'),
('A Garota no Trem', 'Paula Hawkins', 'Suspense', 8.2, 'Um suspense psicológico sobre memória, desaparecimento e versões conflitantes da verdade.'),
('A Ilha do Tesouro', 'Robert Louis Stevenson', 'Aventura', 8.5, 'Uma história de piratas, mapas, coragem e tesouros escondidos.'),
('Percy Jackson e o Ladrão de Raios', 'Rick Riordan', 'Aventura', 8.8, 'Uma aventura moderna envolvendo mitologia grega, ação e humor.'),
('Viagem ao Centro da Terra', 'Júlio Verne', 'Aventura', 8.6, 'Uma exploração fantástica rumo ao interior do planeta.'),
('Steve Jobs', 'Walter Isaacson', 'Biografia', 8.7, 'A trajetória de um dos nomes mais marcantes da tecnologia moderna.'),
('Anne Frank: O Diário de uma Jovem', 'Anne Frank', 'Biografia', 9.3, 'Um relato histórico, humano e sensível escrito durante a Segunda Guerra Mundial.'),
('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 'Drama', 9.4, 'Uma narrativa poética sobre infância, amizade, amor e sentido da vida.'),
('A Menina que Roubava Livros', 'Markus Zusak', 'Drama', 9.1, 'Uma história emocionante ambientada na Alemanha nazista, narrada de forma marcante.');
