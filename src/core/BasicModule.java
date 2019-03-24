package core;

import com.google.inject.AbstractModule;
//import org.glassfish.hk2.utilities.reflection.tools.Constants;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import core.commands.*;
import tools.handler.GuesserBotAnswerHandler;
import tools.handler.MessageHandler;
import tools.handler.RiddlerBotAnswerHandler;
import core.queue.IUserQueue;
import core.queue.UserQueue;
import core.session.ISessionServer;
import core.session.SessionServer;
import core.userdb.IUserDataBase;
import core.userdb.UserDataBase;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import tools.Constants;
import tools.handler.IHandler;
import tools.selector.CommandSelector;
import tools.selector.ICommandSelector;


public class BasicModule extends AbstractModule {

    @Override
    public void configure(){
        bind(IHandler.class).annotatedWith(Names.named("MessageHandler")).to(MessageHandler.class);
        bind(IHandler.class).annotatedWith(Names.named("RiddlerBot")).to(RiddlerBotAnswerHandler.class);
        bind(IHandler.class).annotatedWith(Names.named("GuesserBot")).to(GuesserBotAnswerHandler.class);
        bind(IGameServer.class).to(GameServer.class).asEagerSingleton();
        bind(IUserQueue.class).to(UserQueue.class).asEagerSingleton();
        bind(ISessionServer.class).to(SessionServer.class).asEagerSingleton();
        bind(IUserDataBase.class).to(UserDataBase.class).asEagerSingleton();
        bind(DefaultBotOptions.class).toInstance(defBotOpt());
        bind(ICommandSelector.class).to(CommandSelector.class).asEagerSingleton();

        var binder = Multibinder.newSetBinder(binder(), ICommand.class);
        binder.addBinding().to(GetNumberCommand.class);
        binder.addBinding().to(HelpCommand.class);
        binder.addBinding().to(StartWithRiddlerBotCommand.class);
        binder.addBinding().to(StartWithUserCommand.class);
        binder.addBinding().to(EndSessionCommand.class);
        binder.addBinding().to(StartWithGuesserBotCommand.class);
    }



    public DefaultBotOptions defBotOpt(){
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        botOptions.setProxyHost(Constants.PROXY_HOST);
        botOptions.setProxyPort(Constants.PROXY_PORT);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        return botOptions;
    }
}
