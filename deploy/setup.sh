
sudo apt-get install software-properties-common -y
sudo apt-add-repository ppa:ansible/ansible -y
sudo apt-get update

sudo apt-get install git

sudo apt-get install awscli -y

sudo apt-get install ansible -y

sudo ansible-galaxy install JasonGiedymin.java
sudo ansible-galaxy install JasonGiedymin.scala

sudo ansible-playbook -i ./playbooks/hosts ./playbooks/install.yml
