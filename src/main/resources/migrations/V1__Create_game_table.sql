--
-- Name: category; Type: TABLE; Schema: public; Owner: test; Tablespace: 
--

create table GAME (
    game_id bigint not null,
    created_on timestamp,
    updated_on timestamp,
    primary key (game_id)
);

create table WORD (
    word_id bigint not null,
    name varchar(255) not null,
    score bigint not null,
    created_on timestamp,
    updated_on timestamp,
    primary key (word_id)
);

create sequence GAME_GAME_ID_SEQ 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create sequence GAME_GAME_ID_SEQ 
	START WITH 1 
	INCREMENT BY 1
	NO MINVALUE
    NO MAXVALUE
    CACHE 1;
