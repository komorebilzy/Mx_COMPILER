echo 'Host github.com
	HostName github.com
	ProxyCommand ProxyCommand ssh jump@hk2.wxzheng.com -i ~/.ssh/id_hknode_jump -W %h:%p' > ~/.ssh/config

ssh -T git@github.com
