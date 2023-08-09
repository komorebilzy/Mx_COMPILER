// Generated from D:/IDEA/Compiler/src/Parser\Mx.g4 by ANTLR 4.12.0
package Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, Add=3, Sub=4, Mul=5, Div=6, Mod=7, AddSelf=8, SubSelf=9, 
		Less=10, Greater=11, LEqual=12, GEqual=13, NEqual=14, EEqual=15, And=16, 
		Or=17, Not=18, BOr=19, Xor=20, BAnd=21, BNot=22, RShift=23, LShift=24, 
		LParen=25, RParen=26, LBracket=27, RBracket=28, LBrace=29, RBrace=30, 
		Assign=31, Semi=32, Comma=33, Dot=34, Quote=35, Arrow=36, Int=37, Bool=38, 
		Str=39, Void=40, New=41, Null=42, Class=43, This=44, True=45, False=46, 
		If=47, Else=48, While=49, For=50, Return=51, Break=52, Continue=53, Identifier=54, 
		IntConst=55, StringConst=56, WhiteSpace=57, LineComment=58, ParaComment=59;
	public static final int
		RULE_program = 0, RULE_varDef = 1, RULE_type = 2, RULE_typeName = 3, RULE_varDefUnit = 4, 
		RULE_funcDef = 5, RULE_returnType = 6, RULE_suite = 7, RULE_parameterList = 8, 
		RULE_classDef = 9, RULE_classBuild = 10, RULE_statement = 11, RULE_ifStmt = 12, 
		RULE_whileStmt = 13, RULE_forStmt = 14, RULE_forInit = 15, RULE_exprStmt = 16, 
		RULE_breakStmt = 17, RULE_continueStmt = 18, RULE_returnStmt = 19, RULE_expr = 20, 
		RULE_primary = 21, RULE_newArrayUnit = 22, RULE_exprList = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "varDef", "type", "typeName", "varDefUnit", "funcDef", "returnType", 
			"suite", "parameterList", "classDef", "classBuild", "statement", "ifStmt", 
			"whileStmt", "forStmt", "forInit", "exprStmt", "breakStmt", "continueStmt", 
			"returnStmt", "expr", "primary", "newArrayUnit", "exprList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'?'", "':'", "'+'", "'-'", "'*'", "'/'", "'%'", "'++'", "'--'", 
			"'<'", "'>'", "'<='", "'>='", "'!='", "'=='", "'&&'", "'||'", "'!'", 
			"'|'", "'^'", "'&'", "'~'", "'>>'", "'<<'", "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'='", "';'", "','", "'.'", "'\"'", "'->'", "'int'", "'bool'", 
			"'string'", "'void'", "'new'", "'null'", "'class'", "'this'", "'true'", 
			"'false'", "'if'", "'else'", "'while'", "'for'", "'return'", "'break'", 
			"'continue'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "Add", "Sub", "Mul", "Div", "Mod", "AddSelf", "SubSelf", 
			"Less", "Greater", "LEqual", "GEqual", "NEqual", "EEqual", "And", "Or", 
			"Not", "BOr", "Xor", "BAnd", "BNot", "RShift", "LShift", "LParen", "RParen", 
			"LBracket", "RBracket", "LBrace", "RBrace", "Assign", "Semi", "Comma", 
			"Dot", "Quote", "Arrow", "Int", "Bool", "Str", "Void", "New", "Null", 
			"Class", "This", "True", "False", "If", "Else", "While", "For", "Return", 
			"Break", "Continue", "Identifier", "IntConst", "StringConst", "WhiteSpace", 
			"LineComment", "ParaComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ClassDefContext> classDef() {
			return getRuleContexts(ClassDefContext.class);
		}
		public ClassDefContext classDef(int i) {
			return getRuleContext(ClassDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18025256186806272L) != 0)) {
				{
				setState(51);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(48);
					varDef();
					}
					break;
				case 2:
					{
					setState(49);
					funcDef();
					}
					break;
				case 3:
					{
					setState(50);
					classDef();
					}
					break;
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<VarDefUnitContext> varDefUnit() {
			return getRuleContexts(VarDefUnitContext.class);
		}
		public VarDefUnitContext varDefUnit(int i) {
			return getRuleContext(VarDefUnitContext.class,i);
		}
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			type();
			setState(59);
			varDefUnit();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(60);
				match(Comma);
				setState(61);
				varDefUnit();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<TerminalNode> LBracket() { return getTokens(MxParser.LBracket); }
		public TerminalNode LBracket(int i) {
			return getToken(MxParser.LBracket, i);
		}
		public List<TerminalNode> RBracket() { return getTokens(MxParser.RBracket); }
		public TerminalNode RBracket(int i) {
			return getToken(MxParser.RBracket, i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			typeName();
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBracket) {
				{
				{
				setState(70);
				match(LBracket);
				setState(71);
				match(RBracket);
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public TerminalNode Str() { return getToken(MxParser.Str, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 18015360582156288L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDefUnitContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VarDefUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDefUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDefUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDefUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefUnitContext varDefUnit() throws RecognitionException {
		VarDefUnitContext _localctx = new VarDefUnitContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDefUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(Identifier);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(80);
				match(Assign);
				setState(81);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDefContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			returnType();
			setState(85);
			match(Identifier);
			setState(86);
			match(LParen);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18015360582156288L) != 0)) {
				{
				setState(87);
				parameterList();
				}
			}

			setState(90);
			match(RParen);
			setState(91);
			match(LBrace);
			setState(92);
			suite();
			setState(93);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Void() { return getToken(MxParser.Void, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_returnType);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case Str:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				type();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				match(Void);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SuiteContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 143823684925391632L) != 0)) {
				{
				{
				setState(99);
				statement();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MxParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MxParser.Identifier, i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			type();
			setState(106);
			match(Identifier);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(107);
				match(Comma);
				setState(108);
				type();
				setState(109);
				match(Identifier);
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<ClassBuildContext> classBuild() {
			return getRuleContexts(ClassBuildContext.class);
		}
		public ClassBuildContext classBuild(int i) {
			return getRuleContext(ClassBuildContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(Class);
			setState(117);
			match(Identifier);
			setState(118);
			match(LBrace);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18016460093784064L) != 0)) {
				{
				setState(122);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(119);
					varDef();
					}
					break;
				case 2:
					{
					setState(120);
					classBuild();
					}
					break;
				case 3:
					{
					setState(121);
					funcDef();
					}
					break;
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
			match(RBrace);
			setState(128);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassBuildContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public ClassBuildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBuild; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassBuild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassBuild(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassBuild(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassBuildContext classBuild() throws RecognitionException {
		ClassBuildContext _localctx = new ClassBuildContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classBuild);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(Identifier);
			setState(131);
			match(LParen);
			setState(132);
			match(RParen);
			setState(133);
			match(LBrace);
			setState(134);
			suite();
			setState(135);
			match(RBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public TerminalNode LBrace() { return getToken(MxParser.LBrace, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public TerminalNode RBrace() { return getToken(MxParser.RBrace, 0); }
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public BreakStmtContext breakStmt() {
			return getRuleContext(BreakStmtContext.class,0);
		}
		public ContinueStmtContext continueStmt() {
			return getRuleContext(ContinueStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statement);
		try {
			setState(149);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				match(LBrace);
				setState(138);
				suite();
				setState(139);
				match(RBrace);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				varDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				ifStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(143);
				whileStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(144);
				forStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(145);
				breakStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(146);
				continueStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(147);
				returnStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(148);
				exprStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(If);
			setState(152);
			match(LParen);
			setState(153);
			expr(0);
			setState(154);
			match(RParen);
			setState(155);
			statement();
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(156);
				match(Else);
				setState(157);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(While);
			setState(161);
			match(LParen);
			setState(162);
			expr(0);
			setState(163);
			match(RParen);
			setState(164);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_forStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(For);
			setState(167);
			match(LParen);
			setState(168);
			forInit();
			setState(169);
			exprStmt();
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126230531976463120L) != 0)) {
				{
				setState(170);
				expr(0);
				}
			}

			setState(173);
			match(RParen);
			setState(174);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForInitContext extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_forInit);
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				varDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				exprStmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprStmtContext extends ParserRuleContext {
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprStmtContext exprStmt() throws RecognitionException {
		ExprStmtContext _localctx = new ExprStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_exprStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126230531976463120L) != 0)) {
				{
				setState(180);
				expr(0);
				}
			}

			setState(183);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakStmtContext extends ParserRuleContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public BreakStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStmtContext breakStmt() throws RecognitionException {
		BreakStmtContext _localctx = new BreakStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_breakStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(Break);
			setState(186);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueStmtContext extends ParserRuleContext {
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ContinueStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueStmtContext continueStmt() throws RecognitionException {
		ContinueStmtContext _localctx = new ContinueStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_continueStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(Continue);
			setState(189);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxParser.Semi, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_returnStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(Return);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126230531976463120L) != 0)) {
				{
				setState(192);
				expr(0);
				}
			}

			setState(195);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewExprContext extends ExprContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<NewArrayUnitContext> newArrayUnit() {
			return getRuleContexts(NewArrayUnitContext.class);
		}
		public NewArrayUnitContext newArrayUnit(int i) {
			return getRuleContext(NewArrayUnitContext.class,i);
		}
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Not() { return getToken(MxParser.Not, 0); }
		public TerminalNode BNot() { return getToken(MxParser.BNot, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode AddSelf() { return getToken(MxParser.AddSelf, 0); }
		public TerminalNode SubSelf() { return getToken(MxParser.SubSelf, 0); }
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FuncExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FuncExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TernaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTernaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTernaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitTernaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> LBracket() { return getTokens(MxParser.LBracket); }
		public TerminalNode LBracket(int i) {
			return getToken(MxParser.LBracket, i);
		}
		public List<TerminalNode> RBracket() { return getTokens(MxParser.RBracket); }
		public TerminalNode RBracket(int i) {
			return getToken(MxParser.RBracket, i);
		}
		public ArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitArrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode Dot() { return getToken(MxParser.Dot, 0); }
		public MemberExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtomExprContext extends ExprContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAtomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAtomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAtomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxParser.Mod, 0); }
		public TerminalNode Add() { return getToken(MxParser.Add, 0); }
		public TerminalNode Sub() { return getToken(MxParser.Sub, 0); }
		public TerminalNode LShift() { return getToken(MxParser.LShift, 0); }
		public TerminalNode RShift() { return getToken(MxParser.RShift, 0); }
		public TerminalNode Less() { return getToken(MxParser.Less, 0); }
		public TerminalNode Greater() { return getToken(MxParser.Greater, 0); }
		public TerminalNode LEqual() { return getToken(MxParser.LEqual, 0); }
		public TerminalNode GEqual() { return getToken(MxParser.GEqual, 0); }
		public TerminalNode EEqual() { return getToken(MxParser.EEqual, 0); }
		public TerminalNode NEqual() { return getToken(MxParser.NEqual, 0); }
		public TerminalNode BAnd() { return getToken(MxParser.BAnd, 0); }
		public TerminalNode Xor() { return getToken(MxParser.Xor, 0); }
		public TerminalNode BOr() { return getToken(MxParser.BOr, 0); }
		public TerminalNode And() { return getToken(MxParser.And, 0); }
		public TerminalNode Or() { return getToken(MxParser.Or, 0); }
		public BinaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignExprContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Assign() { return getToken(MxParser.Assign, 0); }
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LParen() { return getToken(MxParser.LParen, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RParen() { return getToken(MxParser.RParen, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PreAddExprContext extends ExprContext {
		public Token op;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode AddSelf() { return getToken(MxParser.AddSelf, 0); }
		public TerminalNode SubSelf() { return getToken(MxParser.SubSelf, 0); }
		public PreAddExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPreAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPreAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPreAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LParen:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(198);
				match(LParen);
				setState(199);
				expr(0);
				setState(200);
				match(RParen);
				}
				break;
			case New:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(202);
				match(New);
				setState(203);
				typeName();
				setState(207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(204);
						newArrayUnit();
						}
						} 
					}
					setState(209);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
				}
				setState(212);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(210);
					match(LParen);
					setState(211);
					match(RParen);
					}
					break;
				}
				}
				break;
			case AddSelf:
			case SubSelf:
				{
				_localctx = new PreAddExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(214);
				((PreAddExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==AddSelf || _la==SubSelf) ) {
					((PreAddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(215);
				expr(15);
				}
				break;
			case Sub:
			case Not:
			case BNot:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(216);
				((UnaryExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4456464L) != 0)) ) {
					((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(217);
				expr(14);
				}
				break;
			case Null:
			case This:
			case True:
			case False:
			case Identifier:
			case IntConst:
			case StringConst:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(218);
				primary();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(282);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(280);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(221);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(222);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 224L) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(223);
						expr(14);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(224);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(225);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Sub) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(226);
						expr(13);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(227);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(228);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==RShift || _la==LShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(229);
						expr(12);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(230);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(231);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15360L) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(232);
						expr(11);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(233);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(234);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==NEqual || _la==EEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(235);
						expr(10);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(236);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(237);
						((BinaryExprContext)_localctx).op = match(BAnd);
						setState(238);
						expr(9);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(239);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(240);
						((BinaryExprContext)_localctx).op = match(Xor);
						setState(241);
						expr(8);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(242);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(243);
						((BinaryExprContext)_localctx).op = match(BOr);
						setState(244);
						expr(7);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(245);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(246);
						((BinaryExprContext)_localctx).op = match(And);
						setState(247);
						expr(6);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(248);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(249);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(250);
						expr(5);
						}
						break;
					case 11:
						{
						_localctx = new TernaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(251);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(252);
						((TernaryExprContext)_localctx).op = match(T__0);
						setState(253);
						expr(0);
						setState(254);
						((TernaryExprContext)_localctx).op = match(T__1);
						setState(255);
						expr(3);
						}
						break;
					case 12:
						{
						_localctx = new AssignExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(257);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(258);
						((AssignExprContext)_localctx).op = match(Assign);
						setState(259);
						expr(2);
						}
						break;
					case 13:
						{
						_localctx = new MemberExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(260);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(261);
						((MemberExprContext)_localctx).op = match(Dot);
						setState(262);
						match(Identifier);
						}
						break;
					case 14:
						{
						_localctx = new ArrayExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(263);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(268); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(264);
								match(LBracket);
								setState(265);
								expr(0);
								setState(266);
								match(RBracket);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(270); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 15:
						{
						_localctx = new FuncExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(273);
						match(LParen);
						setState(275);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126230531976463120L) != 0)) {
							{
							setState(274);
							exprList();
							}
						}

						setState(277);
						match(RParen);
						}
						break;
					case 16:
						{
						_localctx = new UnaryExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(278);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(279);
						((UnaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AddSelf || _la==SubSelf) ) {
							((UnaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(284);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode IntConst() { return getToken(MxParser.IntConst, 0); }
		public TerminalNode StringConst() { return getToken(MxParser.StringConst, 0); }
		public TerminalNode True() { return getToken(MxParser.True, 0); }
		public TerminalNode False() { return getToken(MxParser.False, 0); }
		public TerminalNode Null() { return getToken(MxParser.Null, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_primary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 126228332915195904L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NewArrayUnitContext extends ParserRuleContext {
		public TerminalNode LBracket() { return getToken(MxParser.LBracket, 0); }
		public TerminalNode RBracket() { return getToken(MxParser.RBracket, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NewArrayUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newArrayUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewArrayUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewArrayUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewArrayUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewArrayUnitContext newArrayUnit() throws RecognitionException {
		NewArrayUnitContext _localctx = new NewArrayUnitContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_newArrayUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(LBracket);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 126230531976463120L) != 0)) {
				{
				setState(288);
				expr(0);
				}
			}

			setState(291);
			match(RBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxParser.Comma, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			expr(0);
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(294);
				match(Comma);
				setState(295);
				expr(0);
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 20:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 13);
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 19);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 17);
		case 15:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001;\u012e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0005\u00004\b\u0000\n\u0000\f\u00007\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"?\b\u0001\n\u0001\f\u0001B\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002I\b\u0002\n\u0002\f\u0002L\t\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"S\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"Y\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0003\u0006b\b\u0006\u0001\u0007\u0005\u0007"+
		"e\b\u0007\n\u0007\f\u0007h\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0005\bp\b\b\n\b\f\bs\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0005\t{\b\t\n\t\f\t~\t\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0096\b\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u009f\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00ac\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u00b3\b\u000f\u0001"+
		"\u0010\u0003\u0010\u00b6\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u00c2\b\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u00ce\b\u0014\n\u0014\f\u0014\u00d1\t\u0014\u0001\u0014"+
		"\u0001\u0014\u0003\u0014\u00d5\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u00dc\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0004\u0014\u010d\b\u0014\u000b\u0014"+
		"\f\u0014\u010e\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u0114\b"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u0119\b\u0014\n"+
		"\u0014\f\u0014\u011c\t\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u0122\b\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0005\u0017\u0129\b\u0017\n\u0017\f\u0017\u012c\t\u0017"+
		"\u0001\u0017\u0000\u0001(\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.\u0000\t\u0002\u0000"+
		"%\'66\u0001\u0000\b\t\u0003\u0000\u0004\u0004\u0012\u0012\u0016\u0016"+
		"\u0001\u0000\u0005\u0007\u0001\u0000\u0003\u0004\u0001\u0000\u0017\u0018"+
		"\u0001\u0000\n\r\u0001\u0000\u000e\u000f\u0003\u0000**,.68\u0149\u0000"+
		"5\u0001\u0000\u0000\u0000\u0002:\u0001\u0000\u0000\u0000\u0004E\u0001"+
		"\u0000\u0000\u0000\u0006M\u0001\u0000\u0000\u0000\bO\u0001\u0000\u0000"+
		"\u0000\nT\u0001\u0000\u0000\u0000\fa\u0001\u0000\u0000\u0000\u000ef\u0001"+
		"\u0000\u0000\u0000\u0010i\u0001\u0000\u0000\u0000\u0012t\u0001\u0000\u0000"+
		"\u0000\u0014\u0082\u0001\u0000\u0000\u0000\u0016\u0095\u0001\u0000\u0000"+
		"\u0000\u0018\u0097\u0001\u0000\u0000\u0000\u001a\u00a0\u0001\u0000\u0000"+
		"\u0000\u001c\u00a6\u0001\u0000\u0000\u0000\u001e\u00b2\u0001\u0000\u0000"+
		"\u0000 \u00b5\u0001\u0000\u0000\u0000\"\u00b9\u0001\u0000\u0000\u0000"+
		"$\u00bc\u0001\u0000\u0000\u0000&\u00bf\u0001\u0000\u0000\u0000(\u00db"+
		"\u0001\u0000\u0000\u0000*\u011d\u0001\u0000\u0000\u0000,\u011f\u0001\u0000"+
		"\u0000\u0000.\u0125\u0001\u0000\u0000\u000004\u0003\u0002\u0001\u0000"+
		"14\u0003\n\u0005\u000024\u0003\u0012\t\u000030\u0001\u0000\u0000\u0000"+
		"31\u0001\u0000\u0000\u000032\u0001\u0000\u0000\u000047\u0001\u0000\u0000"+
		"\u000053\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000068\u0001\u0000"+
		"\u0000\u000075\u0001\u0000\u0000\u000089\u0005\u0000\u0000\u00019\u0001"+
		"\u0001\u0000\u0000\u0000:;\u0003\u0004\u0002\u0000;@\u0003\b\u0004\u0000"+
		"<=\u0005!\u0000\u0000=?\u0003\b\u0004\u0000><\u0001\u0000\u0000\u0000"+
		"?B\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000"+
		"\u0000AC\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000CD\u0005 \u0000"+
		"\u0000D\u0003\u0001\u0000\u0000\u0000EJ\u0003\u0006\u0003\u0000FG\u0005"+
		"\u001b\u0000\u0000GI\u0005\u001c\u0000\u0000HF\u0001\u0000\u0000\u0000"+
		"IL\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000"+
		"\u0000K\u0005\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000MN\u0007"+
		"\u0000\u0000\u0000N\u0007\u0001\u0000\u0000\u0000OR\u00056\u0000\u0000"+
		"PQ\u0005\u001f\u0000\u0000QS\u0003(\u0014\u0000RP\u0001\u0000\u0000\u0000"+
		"RS\u0001\u0000\u0000\u0000S\t\u0001\u0000\u0000\u0000TU\u0003\f\u0006"+
		"\u0000UV\u00056\u0000\u0000VX\u0005\u0019\u0000\u0000WY\u0003\u0010\b"+
		"\u0000XW\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001\u0000"+
		"\u0000\u0000Z[\u0005\u001a\u0000\u0000[\\\u0005\u001d\u0000\u0000\\]\u0003"+
		"\u000e\u0007\u0000]^\u0005\u001e\u0000\u0000^\u000b\u0001\u0000\u0000"+
		"\u0000_b\u0003\u0004\u0002\u0000`b\u0005(\u0000\u0000a_\u0001\u0000\u0000"+
		"\u0000a`\u0001\u0000\u0000\u0000b\r\u0001\u0000\u0000\u0000ce\u0003\u0016"+
		"\u000b\u0000dc\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001"+
		"\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000g\u000f\u0001\u0000\u0000"+
		"\u0000hf\u0001\u0000\u0000\u0000ij\u0003\u0004\u0002\u0000jq\u00056\u0000"+
		"\u0000kl\u0005!\u0000\u0000lm\u0003\u0004\u0002\u0000mn\u00056\u0000\u0000"+
		"np\u0001\u0000\u0000\u0000ok\u0001\u0000\u0000\u0000ps\u0001\u0000\u0000"+
		"\u0000qo\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000r\u0011\u0001"+
		"\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000tu\u0005+\u0000\u0000uv\u0005"+
		"6\u0000\u0000v|\u0005\u001d\u0000\u0000w{\u0003\u0002\u0001\u0000x{\u0003"+
		"\u0014\n\u0000y{\u0003\n\u0005\u0000zw\u0001\u0000\u0000\u0000zx\u0001"+
		"\u0000\u0000\u0000zy\u0001\u0000\u0000\u0000{~\u0001\u0000\u0000\u0000"+
		"|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u007f\u0001\u0000"+
		"\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0080\u0005\u001e\u0000\u0000"+
		"\u0080\u0081\u0005 \u0000\u0000\u0081\u0013\u0001\u0000\u0000\u0000\u0082"+
		"\u0083\u00056\u0000\u0000\u0083\u0084\u0005\u0019\u0000\u0000\u0084\u0085"+
		"\u0005\u001a\u0000\u0000\u0085\u0086\u0005\u001d\u0000\u0000\u0086\u0087"+
		"\u0003\u000e\u0007\u0000\u0087\u0088\u0005\u001e\u0000\u0000\u0088\u0015"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0005\u001d\u0000\u0000\u008a\u008b"+
		"\u0003\u000e\u0007\u0000\u008b\u008c\u0005\u001e\u0000\u0000\u008c\u0096"+
		"\u0001\u0000\u0000\u0000\u008d\u0096\u0003\u0002\u0001\u0000\u008e\u0096"+
		"\u0003\u0018\f\u0000\u008f\u0096\u0003\u001a\r\u0000\u0090\u0096\u0003"+
		"\u001c\u000e\u0000\u0091\u0096\u0003\"\u0011\u0000\u0092\u0096\u0003$"+
		"\u0012\u0000\u0093\u0096\u0003&\u0013\u0000\u0094\u0096\u0003 \u0010\u0000"+
		"\u0095\u0089\u0001\u0000\u0000\u0000\u0095\u008d\u0001\u0000\u0000\u0000"+
		"\u0095\u008e\u0001\u0000\u0000\u0000\u0095\u008f\u0001\u0000\u0000\u0000"+
		"\u0095\u0090\u0001\u0000\u0000\u0000\u0095\u0091\u0001\u0000\u0000\u0000"+
		"\u0095\u0092\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000"+
		"\u0095\u0094\u0001\u0000\u0000\u0000\u0096\u0017\u0001\u0000\u0000\u0000"+
		"\u0097\u0098\u0005/\u0000\u0000\u0098\u0099\u0005\u0019\u0000\u0000\u0099"+
		"\u009a\u0003(\u0014\u0000\u009a\u009b\u0005\u001a\u0000\u0000\u009b\u009e"+
		"\u0003\u0016\u000b\u0000\u009c\u009d\u00050\u0000\u0000\u009d\u009f\u0003"+
		"\u0016\u000b\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001"+
		"\u0000\u0000\u0000\u009f\u0019\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005"+
		"1\u0000\u0000\u00a1\u00a2\u0005\u0019\u0000\u0000\u00a2\u00a3\u0003(\u0014"+
		"\u0000\u00a3\u00a4\u0005\u001a\u0000\u0000\u00a4\u00a5\u0003\u0016\u000b"+
		"\u0000\u00a5\u001b\u0001\u0000\u0000\u0000\u00a6\u00a7\u00052\u0000\u0000"+
		"\u00a7\u00a8\u0005\u0019\u0000\u0000\u00a8\u00a9\u0003\u001e\u000f\u0000"+
		"\u00a9\u00ab\u0003 \u0010\u0000\u00aa\u00ac\u0003(\u0014\u0000\u00ab\u00aa"+
		"\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005\u001a\u0000\u0000\u00ae\u00af"+
		"\u0003\u0016\u000b\u0000\u00af\u001d\u0001\u0000\u0000\u0000\u00b0\u00b3"+
		"\u0003\u0002\u0001\u0000\u00b1\u00b3\u0003 \u0010\u0000\u00b2\u00b0\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\u001f\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b6\u0003(\u0014\u0000\u00b5\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b8\u0005 \u0000\u0000\u00b8!\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u00054\u0000\u0000\u00ba\u00bb\u0005 \u0000\u0000\u00bb#"+
		"\u0001\u0000\u0000\u0000\u00bc\u00bd\u00055\u0000\u0000\u00bd\u00be\u0005"+
		" \u0000\u0000\u00be%\u0001\u0000\u0000\u0000\u00bf\u00c1\u00053\u0000"+
		"\u0000\u00c0\u00c2\u0003(\u0014\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c4\u0005 \u0000\u0000\u00c4\'\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c6\u0006\u0014\uffff\uffff\u0000\u00c6\u00c7\u0005\u0019\u0000\u0000"+
		"\u00c7\u00c8\u0003(\u0014\u0000\u00c8\u00c9\u0005\u001a\u0000\u0000\u00c9"+
		"\u00dc\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005)\u0000\u0000\u00cb\u00cf"+
		"\u0003\u0006\u0003\u0000\u00cc\u00ce\u0003,\u0016\u0000\u00cd\u00cc\u0001"+
		"\u0000\u0000\u0000\u00ce\u00d1\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001"+
		"\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000\u0000\u0000\u00d0\u00d4\u0001"+
		"\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005"+
		"\u0019\u0000\u0000\u00d3\u00d5\u0005\u001a\u0000\u0000\u00d4\u00d2\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00dc\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d7\u0007\u0001\u0000\u0000\u00d7\u00dc\u0003"+
		"(\u0014\u000f\u00d8\u00d9\u0007\u0002\u0000\u0000\u00d9\u00dc\u0003(\u0014"+
		"\u000e\u00da\u00dc\u0003*\u0015\u0000\u00db\u00c5\u0001\u0000\u0000\u0000"+
		"\u00db\u00ca\u0001\u0000\u0000\u0000\u00db\u00d6\u0001\u0000\u0000\u0000"+
		"\u00db\u00d8\u0001\u0000\u0000\u0000\u00db\u00da\u0001\u0000\u0000\u0000"+
		"\u00dc\u011a\u0001\u0000\u0000\u0000\u00dd\u00de\n\r\u0000\u0000\u00de"+
		"\u00df\u0007\u0003\u0000\u0000\u00df\u0119\u0003(\u0014\u000e\u00e0\u00e1"+
		"\n\f\u0000\u0000\u00e1\u00e2\u0007\u0004\u0000\u0000\u00e2\u0119\u0003"+
		"(\u0014\r\u00e3\u00e4\n\u000b\u0000\u0000\u00e4\u00e5\u0007\u0005\u0000"+
		"\u0000\u00e5\u0119\u0003(\u0014\f\u00e6\u00e7\n\n\u0000\u0000\u00e7\u00e8"+
		"\u0007\u0006\u0000\u0000\u00e8\u0119\u0003(\u0014\u000b\u00e9\u00ea\n"+
		"\t\u0000\u0000\u00ea\u00eb\u0007\u0007\u0000\u0000\u00eb\u0119\u0003("+
		"\u0014\n\u00ec\u00ed\n\b\u0000\u0000\u00ed\u00ee\u0005\u0015\u0000\u0000"+
		"\u00ee\u0119\u0003(\u0014\t\u00ef\u00f0\n\u0007\u0000\u0000\u00f0\u00f1"+
		"\u0005\u0014\u0000\u0000\u00f1\u0119\u0003(\u0014\b\u00f2\u00f3\n\u0006"+
		"\u0000\u0000\u00f3\u00f4\u0005\u0013\u0000\u0000\u00f4\u0119\u0003(\u0014"+
		"\u0007\u00f5\u00f6\n\u0005\u0000\u0000\u00f6\u00f7\u0005\u0010\u0000\u0000"+
		"\u00f7\u0119\u0003(\u0014\u0006\u00f8\u00f9\n\u0004\u0000\u0000\u00f9"+
		"\u00fa\u0005\u0011\u0000\u0000\u00fa\u0119\u0003(\u0014\u0005\u00fb\u00fc"+
		"\n\u0003\u0000\u0000\u00fc\u00fd\u0005\u0001\u0000\u0000\u00fd\u00fe\u0003"+
		"(\u0014\u0000\u00fe\u00ff\u0005\u0002\u0000\u0000\u00ff\u0100\u0003(\u0014"+
		"\u0003\u0100\u0119\u0001\u0000\u0000\u0000\u0101\u0102\n\u0002\u0000\u0000"+
		"\u0102\u0103\u0005\u001f\u0000\u0000\u0103\u0119\u0003(\u0014\u0002\u0104"+
		"\u0105\n\u0013\u0000\u0000\u0105\u0106\u0005\"\u0000\u0000\u0106\u0119"+
		"\u00056\u0000\u0000\u0107\u010c\n\u0012\u0000\u0000\u0108\u0109\u0005"+
		"\u001b\u0000\u0000\u0109\u010a\u0003(\u0014\u0000\u010a\u010b\u0005\u001c"+
		"\u0000\u0000\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u0108\u0001\u0000"+
		"\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010c\u0001\u0000"+
		"\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000\u010f\u0119\u0001\u0000"+
		"\u0000\u0000\u0110\u0111\n\u0011\u0000\u0000\u0111\u0113\u0005\u0019\u0000"+
		"\u0000\u0112\u0114\u0003.\u0017\u0000\u0113\u0112\u0001\u0000\u0000\u0000"+
		"\u0113\u0114\u0001\u0000\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000"+
		"\u0115\u0119\u0005\u001a\u0000\u0000\u0116\u0117\n\u0010\u0000\u0000\u0117"+
		"\u0119\u0007\u0001\u0000\u0000\u0118\u00dd\u0001\u0000\u0000\u0000\u0118"+
		"\u00e0\u0001\u0000\u0000\u0000\u0118\u00e3\u0001\u0000\u0000\u0000\u0118"+
		"\u00e6\u0001\u0000\u0000\u0000\u0118\u00e9\u0001\u0000\u0000\u0000\u0118"+
		"\u00ec\u0001\u0000\u0000\u0000\u0118\u00ef\u0001\u0000\u0000\u0000\u0118"+
		"\u00f2\u0001\u0000\u0000\u0000\u0118\u00f5\u0001\u0000\u0000\u0000\u0118"+
		"\u00f8\u0001\u0000\u0000\u0000\u0118\u00fb\u0001\u0000\u0000\u0000\u0118"+
		"\u0101\u0001\u0000\u0000\u0000\u0118\u0104\u0001\u0000\u0000\u0000\u0118"+
		"\u0107\u0001\u0000\u0000\u0000\u0118\u0110\u0001\u0000\u0000\u0000\u0118"+
		"\u0116\u0001\u0000\u0000\u0000\u0119\u011c\u0001\u0000\u0000\u0000\u011a"+
		"\u0118\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b"+
		")\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d\u011e"+
		"\u0007\b\u0000\u0000\u011e+\u0001\u0000\u0000\u0000\u011f\u0121\u0005"+
		"\u001b\u0000\u0000\u0120\u0122\u0003(\u0014\u0000\u0121\u0120\u0001\u0000"+
		"\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000"+
		"\u0000\u0000\u0123\u0124\u0005\u001c\u0000\u0000\u0124-\u0001\u0000\u0000"+
		"\u0000\u0125\u012a\u0003(\u0014\u0000\u0126\u0127\u0005!\u0000\u0000\u0127"+
		"\u0129\u0003(\u0014\u0000\u0128\u0126\u0001\u0000\u0000\u0000\u0129\u012c"+
		"\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012a\u012b"+
		"\u0001\u0000\u0000\u0000\u012b/\u0001\u0000\u0000\u0000\u012c\u012a\u0001"+
		"\u0000\u0000\u0000\u001a35@JRXafqz|\u0095\u009e\u00ab\u00b2\u00b5\u00c1"+
		"\u00cf\u00d4\u00db\u010e\u0113\u0118\u011a\u0121\u012a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}