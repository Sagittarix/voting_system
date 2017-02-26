package voting;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by andrius on 2/26/17.
 */

@Configuration
@ComponentScan("voting.aspects")
@EnableAspectJAutoProxy
public class AspectsConfiguration { }
